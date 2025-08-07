package fox.eternity.Item.Items;


import fox.eternity.entity.entities.ChainsEntity;

public interface TrappedState {
    boolean isTrapped();
    void setTrapped(boolean trapped);
    ChainsEntity getTrappedChains();
    void setTrappedChains(ChainsEntity chains);
}
