package com.miningmark48.tieredmagnets.block;

import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticProjector;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

@SuppressWarnings("Duplicates")
public class BlockMagneticProjector extends ContainerBlock {

    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    private static final BooleanProperty POWERED = BooleanProperty.create("powered");

    private static AxisAlignedBB BOUNDING_BOX_ACITVE = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5625D, 0.9375D);
    private static AxisAlignedBB BOUNDING_BOX_INACITVE = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D);

    public BlockMagneticProjector(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(POWERED, false));
    }

//    @Override
//    public AxisAlignedBB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos) {
//        if (state.get(POWERED)) return BOUNDING_BOX_INACITVE;
//        return BOUNDING_BOX_ACITVE;
//    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return super.getShape(p_220053_1_, p_220053_2_, p_220053_3_, p_220053_4_);
    }

//    @Override
//    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
//        if (state.getValue(POWERED)) addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX_INACITVE);
//        addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX_ACITVE);
//    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return super.getCollisionShape(p_220071_1_, p_220071_2_, p_220071_3_, p_220071_4_);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityMagneticProjector(ModBlocks.ModTileEntities.MAGNETIC_PROJECTOR);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
        if (!player.isSneaking()) NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) world.getTileEntity(pos));
        return true;
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState state1, boolean p_220082_5_) {
        super.onBlockAdded(state, world, pos, state1, p_220082_5_);
        this.setDefaultFacing(world, pos, state);
        world.setBlockState(pos, state.with(POWERED, world.isBlockPowered(pos)));
    }

    //    @SuppressWarnings("deprecation")
//    @Override
//    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos changedPos)
//    {
//        worldIn.setBlockState(pos, state.withProperty(POWERED, worldIn.isBlockPowered(pos)));
//    }

    private void setDefaultFacing(World worldIn, BlockPos pos, BlockState state)
    {
        if (!worldIn.isRemote) {
            BlockState iblockstate = worldIn.getBlockState(pos.north());
            BlockState iblockstate1 = worldIn.getBlockState(pos.south());
            BlockState iblockstate2 = worldIn.getBlockState(pos.west());
            BlockState iblockstate3 = worldIn.getBlockState(pos.east());
            Direction enumfacing = state.get(FACING);
            if (enumfacing == Direction.NORTH && iblockstate.isSolid() && !iblockstate1.isSolid()) {
                enumfacing = Direction.SOUTH;
            } else if (enumfacing == Direction.SOUTH && iblockstate1.isSolid() && !iblockstate.isSolid()) {
                enumfacing = Direction.NORTH;
            } else if (enumfacing == Direction.WEST && iblockstate2.isSolid() && !iblockstate3.isSolid()) {
                enumfacing = Direction.EAST;
            } else if (enumfacing == Direction.EAST && iblockstate3.isSolid() && !iblockstate2.isSolid()) {
                enumfacing = Direction.WEST;
            }

            worldIn.setBlockState(pos, state.with(FACING, enumfacing), 2);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
//        worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
    }

//    @SuppressWarnings("deprecation")
//    @Override
//    public IBlockState getStateFromMeta(int meta)
//    {
//        boolean powered = false;
//        if (meta >= 6)
//        {
//            meta -= 6;
//            powered = true;
//        }
//
//        return this.getDefaultState().withProperty(FACING, EnumFacing.values()[meta]).withProperty(POWERED, powered);
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state)
//    {
//        int i = state.getValue(FACING).getIndex();
//
//        if (state.getValue(POWERED))
//        {
//            i += 6;
//        }
//
//        return i;
//    }

//    @Override
//    protected BlockStateContainer createBlockState()
//    {
//        return new BlockStateContainer(this, FACING, POWERED);
//    }

    public void setState(World worldIn, BlockPos pos, boolean isActive)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(POWERED, isActive), 3);

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean isSolid(BlockState state){
        return false;
    }

//    @Override
//    public boolean isOpaqueCube(BlockState state){
//        return false;
//    }

    @Override
    public BlockRenderLayer getRenderLayer(){
        return BlockRenderLayer.CUTOUT;
    }

//    @Override
//    public boolean isFullBlock(BlockState state) {
//        return false;
//    }


    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState state1, boolean p_196243_5_) {
        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tileentity);
            world.updateComparatorOutputLevel(pos, this);
        }

        super.onReplaced(state, world, pos, state1, p_196243_5_);
    }

}
