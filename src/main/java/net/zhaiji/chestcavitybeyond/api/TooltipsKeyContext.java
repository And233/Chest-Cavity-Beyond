package net.zhaiji.chestcavitybeyond.api;

/**
 * 工具提示按键上下文
 */
public class TooltipsKeyContext {
    private boolean isKeyShiftDown;
    private boolean isKeyCtrlDown;

    public TooltipsKeyContext(boolean isKeyShiftDown, boolean isKeyCtrlDown) {
        this.isKeyShiftDown = isKeyShiftDown;
        this.isKeyCtrlDown = isKeyCtrlDown;
    }

    public boolean isKeyShiftDown() {
        return isKeyShiftDown;
    }

    public boolean isKeyCtrlDown() {
        return isKeyCtrlDown;
    }
}
