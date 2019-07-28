package com.miningmark48.tieredmagnets.block;

import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.init.ModGui;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockMagneticInsulator extends ContainerBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public BlockMagneticInsulator(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(POWERED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
        builder.add(POWERED);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityMagneticInsulator(ModBlocks.ModTileEntities.MAGNETIC_INSULATOR);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
        if (!player.isSneaking()) ModGui.MAGNETIC_INSULATOR.openContainer(player, world, pos);
        return true;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState state2, boolean p_220082_5_) {
        super.onBlockAdded(state, world, pos, state2, p_220082_5_);
        this.setDefaultFacing(world, pos, state);
        world.setBlockState(pos, state.with(POWERED, world.isBlockPowered(pos)));
    }


//    @SuppressWarnings("deprecation")
//    @Override
//    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos changedPos)
//    {
//        worldIn.setBlockState(pos, state.withProperty(POWERED, worldIn.isBlockPowered(pos)));
//    }

    @SuppressWarnings("Duplicates")
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
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
//        world.setBlockState(pos, state.with(FACING, Direction.getDirectionFromEntityLiving(pos, placer)), 2);
    }

//    @SuppressWarnings("deprecation")
//    @Override
//    public BlockState getStateFromMeta(int meta)
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

    @SuppressWarnings("Duplicates")
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
}
