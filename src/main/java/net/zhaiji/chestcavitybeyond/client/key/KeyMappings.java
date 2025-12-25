package net.zhaiji.chestcavitybeyond.client.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;

public class KeyMappings {
    public static final String KEY_CATEGORY_TRANSLATABLE = "key.chestcavitybeyond.categories";
    public static final String OPEN_SKILL_GUI_TRANSLATABLE = "key.chestcavitybeyond.open_skill_gui";
    public static final String USE_ORGAN_SKILL_TRANSLATABLE = "key.chestcavitybeyond.use_organ_skill";

    // 打开技能界面
    public static final KeyMapping OPEN_SKILL_GUI = new KeyMapping(
            OPEN_SKILL_GUI_TRANSLATABLE,
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_V,
            KEY_CATEGORY_TRANSLATABLE
    );

    // 使用器官技能
    public static final KeyMapping USE_ORGAN_SKILL = new KeyMapping(
            USE_ORGAN_SKILL_TRANSLATABLE,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_C,
            KEY_CATEGORY_TRANSLATABLE
    );
}
