package net.zhaiji.chestcavitybeyond.client.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;

import java.util.ArrayList;
import java.util.List;

public class KeyMappings {
    public static final String KEY_CATEGORY_TRANSLATABLE = "key.chestcavitybeyond.categories";
    public static final String OPEN_SKILL_GUI_TRANSLATABLE = "key.chestcavitybeyond.open_skill_gui";
    public static final String USE_ORGAN_SKILL_TRANSLATABLE = "key.chestcavitybeyond.use_organ_skill";
    public static final String USE_ORGAN_SKILLS_TRANSLATABLE = "key.chestcavitybeyond.use_organ_skill_";

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

    // TODO 按键
    public static final List<KeyMapping> USE_SKILLS = new ArrayList<>();

    static {
        for (int i = 0; i < 27; i++) {
            USE_SKILLS.add(
                    new KeyMapping(
                            USE_ORGAN_SKILLS_TRANSLATABLE + i,
                            KeyConflictContext.IN_GAME,
                            InputConstants.Type.KEYSYM,
                            -1,
                            KEY_CATEGORY_TRANSLATABLE
                    )
            );
        }
    }
}
