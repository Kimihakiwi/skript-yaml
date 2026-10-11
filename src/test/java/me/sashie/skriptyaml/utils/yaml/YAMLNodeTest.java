package me.sashie.skriptyaml.utils.yaml;

import me.sashie.skriptyaml.utils.SkriptYamlUtils;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class YAMLNodeTest {
    @Test
    void nestedPaths() {
        YAMLNode node = new YAMLNode(new LinkedHashMap<>(), false);
        node.setProperty("player.stats.level", 10);
        node.setProperty("player.stats.health", 20);
        assertEquals(10, node.getProperty("player.stats.level"));
        assertTrue(node.getAllKeys().contains("player.stats.health"));
        node.removeProperty("player.stats.level");
        assertNull(node.getProperty("player.stats.level"));
        assertEquals(20, node.getProperty("player.stats.health"));
        assertFalse(node.getAllKeys().contains("player.stats.level"));
    }

    @Test
    void clearResetsState() {
        YAMLNode node = new YAMLNode(new LinkedHashMap<>(), false);
        node.setProperty("one.two", 2);
        node.markUnmodified();
        node.clear();
        assertTrue(node.getMap().isEmpty());
        assertTrue(node.getAllKeys().isEmpty());
        assertTrue(node.isModified());
    }

    @Test
    void uuidConversion() {
        UUID id = UUID.randomUUID();
        assertEquals(id, SkriptYamlUtils.convertUUIDs(id.toString()));
        assertEquals("not-a-uuid", SkriptYamlUtils.convertUUIDs("not-a-uuid"));
    }
}
