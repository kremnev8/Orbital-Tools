package net.orbitallabs.structures;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.orbitallabs.blocks.BlockContainerMod;
import net.orbitallabs.tiles.TileEntityInfo;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OreDictItemStack;

public abstract class StructureTemplate extends Structure {

	public static DataFixer fixer = DataFixesManager.createFixer();
	public static PlacementSettings sett = new PlacementSettings();

	public class StructureProcessor implements ITemplateProcessor {

		public BlockPos infopos;
		public BlockPos rinfopos;

		public BlockPos plPos;
		public String sname;
		public EnumFacing dir;
		public int rot;
		public Block filler;

		public StructureProcessor(BlockPos plPos, String Strname, EnumFacing dir, int rot, Block filler) {
			this.plPos = plPos;
			this.sname = Strname;
			this.dir = dir;
			this.rot = rot;
			this.filler = filler;
		}

		@Nullable
		public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
			if (blockInfoIn.blockState.getBlock() != filler) {
				if (blockInfoIn.blockState.getBlock() == BlockContainerMod.BlockInfo.getBlockState().getBlock()) {
					infopos = pos;
				}
				if (blockInfoIn.blockState.getBlock() == BlockContainerMod.BlockRemoveInfo.getBlockState().getBlock()) {
					rinfopos = pos;
				}
				if (blockInfoIn.blockState.getBlock() == BlockContainerMod.BlockRemoveInfo.getBlockState().getBlock()
						&& infopos != null) {
					NBTTagCompound tmp = new NBTTagCompound();
					tmp.setInteger("x", infopos.getX());
					tmp.setInteger("y", infopos.getY());
					tmp.setInteger("z", infopos.getZ());
					blockInfoIn.tileentityData.setTag("infopos", tmp);
				}
				if (blockInfoIn.blockState.getBlock() == BlockContainerMod.BlockInfo.getBlockState().getBlock()
						&& rinfopos != null) {
					NBTTagCompound tmp = new NBTTagCompound();
					tmp.setInteger("x", rinfopos.getX());
					tmp.setInteger("y", rinfopos.getY());
					tmp.setInteger("z", rinfopos.getZ());
					blockInfoIn.tileentityData.setTag("rinfopos", tmp);

					NBTTagCompound obj = new NBTTagCompound();
					obj.setInteger("DIR", dir.getIndex());
					obj.setInteger("ROT", rot);
					obj.setIntArray("POS", TileEntityInfo.getInt(plPos));
					obj.setString("OBJ", sname);
					blockInfoIn.tileentityData.setTag("OBJ", obj);
				}
				return blockInfoIn;
			}
			return null;
		}
	}

	public StructureTemplate() {
		super(false);
	}

	public static Rotation getRotation(EnumFacing dir) {
		switch (dir) {
		case WEST:
			return Rotation.CLOCKWISE_90;
		case NORTH:
			return Rotation.CLOCKWISE_180;
		case EAST:
			return Rotation.COUNTERCLOCKWISE_90;
		default:
			return Rotation.NONE;
		}
	}

	public Template loadTemplate(ResourceLocation loc, Rotation rot, boolean clear, Block filler) {
		try {
			InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream();
			NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(stream);

			if (!nbttagcompound.hasKey("DataVersion", 99)) {
				nbttagcompound.setInteger("DataVersion", 500);
			}

			AdvancedTemplate template = new AdvancedTemplate(rot, clear, filler);
			template.read(fixer.process(FixTypes.STRUCTURE, nbttagcompound));
			return template;
		} catch (IOException e) {
			OTLoger.logWarn(e.getMessage());
		}
		return null;
	}

	@Override
	public void Build(World world, EnumFacing dir, BlockPos pos) {
		try {
			BlockPos tpos = new BlockPos(-4, -4, 0);
			Rotation rot = getRotation(dir);
			tpos = pos.add(tpos.rotate(rot));
			Template template = loadTemplate(getTemplateFile(), rot, false, getFillerBlock());
			sett.setIgnoreEntities(true).setIntegrity(1).setRotation(rot);
			template.addBlocksToWorld(world, tpos,
					new StructureProcessor(pos, this.getUnlocalizedName(), dir, placementRotation, getFillerBlock()),
					sett, 2);
		} catch (Exception e) {
			OTLoger.logWarn(e.getMessage());
		}
	}

	@Override
	public void deconstruct(World world, EnumFacing dir, BlockPos pos) {
		try {
			BlockPos tpos = new BlockPos(-4, -4, 0);
			Rotation rot = getRotation(dir);
			tpos = pos.add(tpos.rotate(rot));
			Template template = loadTemplate(getTemplateFile(), rot, true, getFillerBlock());
			sett.setIgnoreEntities(true).setIntegrity(1).setRotation(rot);
			template.addBlocksToWorld(world, tpos,
					new StructureProcessor(pos, this.getUnlocalizedName(), dir, placementRotation, getFillerBlock()),
					sett, 2);
		} catch (Exception e) {
			OTLoger.logWarn(e.getMessage());
		}
	}

	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos pos, int meta) {
		return true;
	}

	@Override
	public void ClearWay(World world, EnumFacing dir, BlockPos pos) {

	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public abstract String getName();

	@Override
	public abstract String getUnlocalizedName();

	@Override
	public abstract NonNullList<OreDictItemStack> getRequiredItems();

	public abstract ResourceLocation getTemplateFile();

	public Block getFillerBlock() {
		return Blocks.STONE;
	}

}
