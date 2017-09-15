
package net.orbitallabs.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import cofh.thermaldynamics.block.BlockDuct;
import cofh.thermaldynamics.duct.tiles.TileDuctEnergy;
import cofh.thermaldynamics.duct.tiles.TileDuctItem;
import cofh.thermaldynamics.duct.tiles.TileEnergyDuctSuper;
import ic2.core.block.wiring.TileEntityCable;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.renderer.models.ModelArmorStand;
import net.orbitallabs.renderer.models.ModelDummy;
import net.orbitallabs.tiles.TileEntityArmorStand;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class TileEntityArmorStandRenderer extends TileEntitySpecialRenderer<TileEntityArmorStand> {
	
	private static final ResourceLocation rl = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/blocks/armorstand.png");
	private ModelArmorStand model = new ModelArmorStand();
	public int rot = 0;
	
	private float modelScale = 0.0666667F;
	private boolean helmLeatherLayer = false;
	private boolean thuamcraftRobeChest = false;
	private boolean legLeatherLayer = false;
	private boolean thaumcraftRobeLegs = false;
	private boolean thaumcraftRobeFoot = false;
	private static String[] armorArray;
	private AbstractClientPlayer steve;
	private boolean renderSteve = false;
	private ItemStack pumpkinTester = new ItemStack(Blocks.PUMPKIN);
	private Item pumkingetting = Item.getItemFromBlock(Blocks.PUMPKIN);
	private ModelDummy modelDummy = new ModelDummy();
	public static String[] defaultHelmNames = { "item.helmetCloth", "item.helmetIron", "item.helmetChain", "item.helmetGold", "item.helmetDiamond" };
	public static String[] defaultHelmArmorPaths = { "textures/models/armor/leather_layer_1.png", "textures/models/armor/iron_layer_1.png",
			"textures/models/armor/chainmail_layer_1.png", "textures/models/armor/gold_layer_1.png", "textures/models/armor/diamond_layer_1.png" };
	public static String[] defaultChestNames = { "item.chestplateCloth", "item.chestplateIron", "item.chestplateChain", "item.chestplateGold", "item.chestplateDiamond" };
	public static String[] defaultChestArmorPaths = { "textures/models/armor/leather_layer_1.png", "textures/models/armor/iron_layer_1.png",
			"textures/models/armor/chainmail_layer_1.png", "textures/models/armor/gold_layer_1.png", "textures/models/armor/diamond_layer_1.png" };
	public static String[] defaultLegNames = { "item.leggingsCloth", "item.leggingsIron", "item.leggingsChain", "item.leggingsGold", "item.leggingsDiamond" };
	public static String[] defaultLegArmorPaths = { "textures/models/armor/leather_layer_2.png", "textures/models/armor/iron_layer_2.png",
			"textures/models/armor/chainmail_layer_2.png", "textures/models/armor/gold_layer_2.png", "textures/models/armor/diamond_layer_2.png" };
	public static String[] defaultBootNames = { "item.bootsCloth", "item.bootsIron", "item.bootsChain", "item.bootsGold", "item.bootsDiamond" };
	public static String[] defaultBootArmorPaths = { "textures/models/armor/leather_layer_1.png", "textures/models/armor/iron_layer_1.png",
			"textures/models/armor/chainmail_layer_1.png", "textures/models/armor/gold_layer_1.png", "textures/models/armor/diamond_layer_1.png" };
	private int degreeAngle;
	public static final ResourceLocation GLINT_PNG = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	
	public void renderTileEntityAt(TileEntityArmorStand te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		GL11.glTranslatef(0, 0.01F, 0);
		GL11.glDisable(GL11.GL_CULL_FACE);
		rot = te.getBlockMetadata();
		if (rot == 0)
		{
			degreeAngle = 180;
		} else if (rot == 3)
		{
			degreeAngle = 270;
		} else if (rot == 2)
		{
			degreeAngle = 0;
		} else if (rot == 1)
		{
			degreeAngle = 90;
		}
		
		//	float scale = 0.0625F;
		
		if (this.steve == null)
		{
			this.steve = new AbstractSteve(te.getWorld());
		}
		RenderManager mng = Minecraft.getMinecraft().getRenderManager();
		
		this.steve.posX = 0;
		this.steve.posY = 0;
		this.steve.posZ = 0;
		
		TileEntityArmorStand standTile = (TileEntityArmorStand) te;
		ItemStack helmStack = standTile.getArmor(0);
		ItemStack chestStack = standTile.getArmor(1);
		ItemStack legginsStack = standTile.getArmor(2);
		ItemStack bootsStack = standTile.getArmor(3);
		this.renderSteve = false;
		this.steve.inventory.armorInventory.set(3, ItemStack.EMPTY);
		this.steve.inventory.armorInventory.set(2, ItemStack.EMPTY);
		this.steve.inventory.armorInventory.set(1, ItemStack.EMPTY);
		this.steve.inventory.armorInventory.set(0, ItemStack.EMPTY);
		if (helmStack != null)
		{
			Item helmItem = helmStack.getItem();
			if (((helmItem instanceof ItemSkull)) || (helmItem == this.pumkingetting))
			{
				this.steve.inventory.armorInventory.set(0, helmStack);
				this.renderSteve = true;
			}
			if ((helmItem instanceof ItemArmor))
			{
				float colorBase = 1.0F;
				ItemArmor armorHelm = (ItemArmor) helmItem;
				EntityEquipmentSlot aType = armorHelm.armorType;
				ModelBiped helmModel = helmItem.getArmorModel(steve, helmStack, aType, modelDummy);
				boolean isEnchanted = armorHelm.hasEffect(helmStack);
				String helmTexture = ForgeHooksClient.getArmorTexture(this.steve, helmStack, getArmor(helmStack.getUnlocalizedName(), aType), aType, null);
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.549999952316284D, z + 0.5D);
				GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				this.helmLeatherLayer = false;
				if (helmStack.getUnlocalizedName().contains("item.helmetCloth"))
				{
					int var9 = armorHelm.getColor(helmStack);
					float var10 = (var9 >> 16 & 0xFF) / 255.0F;
					float var11 = (var9 >> 8 & 0xFF) / 255.0F;
					float var12 = (var9 & 0xFF) / 255.0F;
					GL11.glColor3f(colorBase * var10, colorBase * var11, colorBase * var12);
					this.helmLeatherLayer = true;
				}
				if (helmModel != null)
				{
					this.steve.inventory.armorInventory.set(aType.getIndex(), helmStack);
					this.renderSteve = true;
					//bindTexture(new ResourceLocation(helmTexture));
					//helmModel.render(steve, steve.limbSwing, steve.limbSwingAmount, steve.getAge(), steve.rotationYawHead, 0, 0.0625F);
				} else
				{
					GL11.glScalef(1.02F, 1.02F, 1.02F);
					bindTexture(new ResourceLocation(helmTexture));
					this.modelDummy.renderHead();
					if (this.helmLeatherLayer)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("textures/models/armor/leather_layer_1_overlay.png"));
						this.modelDummy.renderHead();
					}
				}
				GL11.glEnable(GL11.GL_LIGHTING);
				if (isEnchanted)
				{
					enchant(0);
				}
				GL11.glPopMatrix();
			}
		}
		if (chestStack != null)
		{
			Item cuirassItem = chestStack.getItem();
			if ((cuirassItem instanceof ItemArmor))
			{
				float colorBase = 1.0F;
				ItemArmor armorCuirass = (ItemArmor) cuirassItem;
				EntityEquipmentSlot aType = armorCuirass.armorType;
				
				ModelBiped cuirassModel = cuirassItem.getArmorModel(steve, chestStack, aType, modelDummy);
				
				String cuirassTexture = ForgeHooksClient.getArmorTexture(this.steve, chestStack, getArmor(chestStack.getUnlocalizedName(), aType), aType, null);
				boolean isEnchanted = armorCuirass.hasEffect(chestStack);
				
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.600000023841858D, z + 0.5D);
				GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				this.thuamcraftRobeChest = false;
				if ((chestStack.getUnlocalizedName().contains("item.chestplateCloth")) || (chestStack.getUnlocalizedName().contains("item.ItemChestplateRobe")))
				{
					int var9 = armorCuirass.getColor(chestStack);
					float var10 = (var9 >> 16 & 0xFF) / 255.0F;
					float var11 = (var9 >> 8 & 0xFF) / 255.0F;
					float var12 = (var9 & 0xFF) / 255.0F;
					GL11.glColor3f(colorBase * var10, colorBase * var11, colorBase * var12);
					if (chestStack.getUnlocalizedName().contains("item.ItemChestplateRobe"))
					{
						this.thuamcraftRobeChest = true;
					}
				}
				//		String tchoverTest = cuirassItem.toString();
				if (cuirassModel != null)
				{
					this.steve.inventory.armorInventory.set(aType.getIndex(), chestStack);
					this.renderSteve = true;
					//bindTexture(new ResourceLocation(cuirassTexture));
					//cuirassModel.render(steve, steve.limbSwing, steve.limbSwingAmount, steve.getAge(), steve.rotationYawHead, 0, 0.0625F);
				} else
				{
					GL11.glScalef(1.12F, 1.12F, 1.12F);
					bindTexture(new ResourceLocation(cuirassTexture));
					this.modelDummy.renderChest();
					if (this.thuamcraftRobeChest)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("thaumcraft:textures/models/robes_1_overlay.png"));
						this.modelDummy.renderChest();
					}
				}
				GL11.glEnable(GL11.GL_LIGHTING);
				if (isEnchanted)
				{
					enchant(1);
				}
				GL11.glPopMatrix();
			}
		}
		if (legginsStack != null)
		{
			Item greavesItem = legginsStack.getItem();
			if ((greavesItem instanceof ItemArmor))
			{
				float colorBase = 1.0F;
				ItemArmor armorGreaves = (ItemArmor) greavesItem;
				EntityEquipmentSlot aType = armorGreaves.armorType;
				
				ModelBiped greavesModel = greavesItem.getArmorModel(this.steve, legginsStack, aType, modelDummy);
				
				String greavesTexture = ForgeHooksClient.getArmorTexture(this.steve, legginsStack, getArmor(legginsStack.getUnlocalizedName(), aType), aType, null);
				boolean isEnchanted = armorGreaves.hasEffect(legginsStack);
				
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.799999952316284D, z + 0.5D);
				GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				this.legLeatherLayer = false;
				this.thaumcraftRobeLegs = false;
				if ((legginsStack.getUnlocalizedName().contains("item.leggingsCloth")) || (legginsStack.getUnlocalizedName().contains("item.ItemLeggingsRobe")))
				{
					int var9 = armorGreaves.getColor(legginsStack);
					float var10 = (var9 >> 16 & 0xFF) / 255.0F;
					float var11 = (var9 >> 8 & 0xFF) / 255.0F;
					float var12 = (var9 & 0xFF) / 255.0F;
					GL11.glColor3f(colorBase * var10, colorBase * var11, colorBase * var12);
					if (legginsStack.getUnlocalizedName().contains("item.ItemLeggingsRobe"))
					{
						this.thaumcraftRobeLegs = true;
						GL11.glTranslated(0.0D, 0.21D, 0.0D);
					} else
					{
						this.legLeatherLayer = true;
					}
				}
				if (greavesModel != null)
				{
					this.steve.inventory.armorInventory.set(aType.getIndex(), legginsStack);
					this.renderSteve = true;
					//bindTexture(new ResourceLocation(greavesTexture));
					//greavesModel.render(steve, steve.limbSwing, steve.limbSwingAmount, steve.getAge(), steve.rotationYawHead, 0, 0.0625F);
				} else
				{
					GL11.glScalef(1.05F, 1.05F, 1.05F);
					bindTexture(new ResourceLocation(greavesTexture));
					this.modelDummy.renderLegs();
					if (this.legLeatherLayer)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("textures/models/armor/leather_layer_2_overlay.png"));
						this.modelDummy.renderLegs();
					}
					if (this.thaumcraftRobeLegs)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("thaumcraft:textures/models/robes_2_overlay.png"));
						this.modelDummy.renderLegs();
					}
				}
				GL11.glEnable(GL11.GL_LIGHTING);
				if (isEnchanted)
				{
					enchant(2);
				}
				GL11.glPopMatrix();
			}
		}
		if (bootsStack != null)
		{
			Item bootsItem = bootsStack.getItem();
			if ((bootsItem instanceof ItemArmor))
			{
				float colorBase = 1.0F;
				ItemArmor armorBoots = (ItemArmor) bootsItem;
				EntityEquipmentSlot aType = armorBoots.armorType;
				
				ModelBiped bootsModel = bootsItem.getArmorModel(this.steve, bootsStack, aType, modelDummy);
				String bootsTexture = ForgeHooksClient.getArmorTexture(this.steve, bootsStack, getArmor(bootsStack.getUnlocalizedName(), aType), aType, null);
				boolean isEnchanted = armorBoots.hasEffect(bootsStack);
				
				GL11.glPushMatrix();
				GL11.glTranslated(x + 0.5D, y + 1.7D, z + 0.5D);
				GL11.glRotatef(this.degreeAngle, 0.0F, 2.0F, 0.0F);
				GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				this.thaumcraftRobeFoot = false;
				if ((bootsStack.getUnlocalizedName().contains("item.bootsCloth")) || (bootsStack.getUnlocalizedName().contains("item.ItemBootsRobe")))
				{
					int var9 = armorBoots.getColor(bootsStack);
					float var10 = (var9 >> 16 & 0xFF) / 255.0F;
					float var11 = (var9 >> 8 & 0xFF) / 255.0F;
					float var12 = (var9 & 0xFF) / 255.0F;
					GL11.glColor3f(colorBase * var10, colorBase * var11, colorBase * var12);
					if (bootsStack.getUnlocalizedName().contains("item.ItemBootsRobe"))
					{
						this.thaumcraftRobeFoot = true;
					}
				}
				if (bootsModel != null)
				{
					this.steve.inventory.armorInventory.set(aType.getIndex(), bootsStack);
					this.renderSteve = true;
					//bindTexture(new ResourceLocation(bootsTexture));
					//bootsModel.render(steve, steve.limbSwing, steve.limbSwingAmount, steve.getAge(), steve.rotationYawHead, 0, 0.0625F);
				} else
				{
					bindTexture(new ResourceLocation(bootsTexture));
					this.modelDummy.renderLegs();
					if (this.thaumcraftRobeFoot)
					{
						GL11.glColor3f(1.0F, 1.0F, 1.0F);
						bindTexture(new ResourceLocation("thaumcraft:textures/models/robes_1_overlay.png"));
						this.modelDummy.renderLegs();
					}
				}
				GL11.glEnable(GL11.GL_LIGHTING);
				if (isEnchanted)
				{
					enchant(3);
				}
				GL11.glPopMatrix();
			}
		}
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslatef(0, -0.01F, 0);
		rot = te.getBlockMetadata();
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
		if (rot == 0)
		{
			GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
		} else if (rot == 3)
		{
			GL11.glRotatef(180, -1.0F, 0.0F, 1.0F);
		} else if (rot == 2)
		{
			GL11.glRotatef(180, 0.0F, 0.0F, -1.0F);
		} else if (rot == 1)
		{
			GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
		}
		
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindTexture(rl);
		model.render(0.0625F);
		
		World world = te.getWorld();
		for (int l = 0; l < 4; l++)
		{
			int meta = world.getBlockState(te.getPos()).getBlock().getMetaFromState(world.getBlockState(te.getPos()));
			
			int o = l + meta;
			if (o > 3)
			{
				o -= 4;
			}
			
			Block bl = world.getBlockState(
					new BlockPos(te.getPos().getX() + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.getPos().getY(), te.getPos().getZ() + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0)))
					.getBlock();
			
			TileEntity ent = world.getTileEntity(
					new BlockPos(te.getPos().getX() + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.getPos().getY(), te.getPos().getZ() + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0)));
			if (bl == GCBlocks.fuelLoader)
			{
				model.render("fuel", l + 3 > 3 ? (l - 4) + 3 : l + 3);
			}
			if (bl == GCBlocks.aluminumWire)
			{
				model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
			}
			if (EnergyConfigHandler.isIndustrialCraft2Loaded() && ent instanceof TileEntityCable)
			{
				model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
			}
			/*	if (EnergyConfigHandler.isBuildcraftReallyLoaded() && bl == BuildCraftTransport.genericPipeBlock)
				{
					if (BlockGenericPipe.getPipe(world, te.xCoord + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.yCoord, te.zCoord + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0)) == null
							|| BlockGenericPipe.getPipe(world, te.xCoord + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.yCoord,
									te.zCoord + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0)).transport instanceof PipeTransportFluids)
					{
						
					} else
					{
						model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
					}
				}*/
			if (Loader.isModLoaded("thermaldynamics") && bl instanceof BlockDuct)
			{
				TileEntity te2 = world.getTileEntity(new BlockPos(te.getPos().getX() + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.getPos().getY(),
						te.getPos().getZ() + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0)));
				if (te2 instanceof TileDuctEnergy || te2 instanceof TileDuctItem && !(te2 instanceof TileEnergyDuctSuper))
				{
					model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
				} else if (te2 instanceof TileEnergyDuctSuper)
				{
					model.render("fuel", l + 3 > 3 ? (l - 4) + 3 : l + 3);
				}
			}
			/*if (Loader.isModLoaded("EnderIO") && bl instanceof BlockConduitBundle)
			{
				TileConduitBundle te2 = (TileConduitBundle) world.getTileEntity(te.xCoord + (o == 0 || o == 2 ? o == 2 ? 1 : -1 : 0), te.yCoord,
						te.zCoord + (o == 1 || o == 3 ? o == 3 ? 1 : -1 : 0));
				if (te2 != null && (te2.hasType(PowerConduit.class) || te2.hasType(ItemConduit.class)))
				{
					model.render("wire", l + 3 > 3 ? (l - 4) + 3 : l + 3);
				}
			}*/
		}
		
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		
		if (this.renderSteve)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) 0.5F, (float) 0.15F, (float) 0.5F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(this.degreeAngle - 180, 0.0F, 1.0F, 0.0F);
			
			float f = steve.prevRotationYaw + (steve.rotationYaw - steve.prevRotationYaw) * partialTicks;
			int i = steve.getBrightnessForRender(partialTicks);
			
			int j = i % 65536;
			int k = i / 65536;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mng.doRenderEntity(steve, 0, 0, 0, f, partialTicks, true);
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}
	
	public String getArmor(String itemStackName, EntityEquipmentSlot aType)
	{
		String armorDefault = "textures/models/armor/iron_layer_1.png";
		String armorLegDefault = "textures/models/armor/iron_layer_1.png";
		//	String armorName = itemStackName.toLowerCase();
		switch (aType.getIndex()) {
		case 3:
			String armorReturn;
			for (int x = 0; x < defaultHelmNames.length; x++)
			{
				if (defaultHelmNames[x].contentEquals(itemStackName))
				{
					armorReturn = defaultHelmArmorPaths[x];
					return armorReturn;
				}
				armorReturn = armorDefault;
			}
			break;
		case 2:
			for (int x = 0; x < defaultChestNames.length; x++)
			{
				if (defaultChestNames[x].contentEquals(itemStackName))
				{
					armorReturn = defaultChestArmorPaths[x];
					return armorReturn;
				}
				armorReturn = armorDefault;
			}
			break;
		case 1:
			for (int x = 0; x < defaultLegNames.length; x++)
			{
				if (defaultLegNames[x].contentEquals(itemStackName))
				{
					armorReturn = defaultLegArmorPaths[x];
					return armorReturn;
				}
				armorReturn = armorLegDefault;
			}
			break;
		case 0:
			for (int x = 0; x < defaultBootNames.length; x++)
			{
				if (defaultBootNames[x].contentEquals(itemStackName))
				{
					armorReturn = defaultBootArmorPaths[x];
					return armorReturn;
				}
				armorReturn = armorDefault;
			}
			break;
		}
		return armorDefault;
	}
	
	public void enchant(int armorType)
	{
		float tickModifier = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 48.0F;
		bindTexture(GLINT_PNG);
		GL11.glEnable(3042);
		float var20 = 0.5F;
		GL11.glColor4f(var20, var20, var20, 1.0F);
		GL11.glDepthFunc(514);
		GL11.glDepthMask(false);
		for (int var21 = 0; var21 < 2; var21++)
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			float var22 = 0.76F;
			GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
			GL11.glBlendFunc(768, 1);
			GL11.glMatrixMode(5890);
			GL11.glLoadIdentity();
			float var23 = tickModifier * (0.001F + var21 * 0.003F) * 20.0F;
			float var24 = 0.3333333F;
			GL11.glScalef(var24, var24, var24);
			GL11.glRotatef(30.0F - var21 * 60.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.0F, var23, 0.0F);
			GL11.glMatrixMode(5888);
			switch (armorType) {
			case 0:
				this.modelDummy.renderHead();
				break;
			case 1:
				this.modelDummy.renderChest();
				break;
			case 2:
				this.modelDummy.renderLegs();
				break;
			case 3:
				this.modelDummy.renderLegs();
			}
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glMatrixMode(GL11.GL_TEXTURE);
		GL11.glDepthMask(true);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
	
}