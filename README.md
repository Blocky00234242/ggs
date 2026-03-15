# Item Collector — Fabric 1.21.1

Walks the player toward the nearest valuable item on the ground.

**Targeted items:** Diamond · Emerald · Gold Ingot · Iron Ingot · Netherite Ingot · Netherite Scrap · Ancient Debris

---

## Get the JAR via GitHub Actions (no local build needed)

1. Create a **free** GitHub account at https://github.com
2. Click **New repository** → name it `item-collector` → **Create repository**
3. Push this project:
   ```bash
   cd item-collector-1.21
   git init
   git add .
   git commit -m "Initial mod"
   git remote add origin https://github.com/YOUR_USERNAME/item-collector.git
   git push -u origin main
   ```
4. Go to your repo → **Actions** tab → watch the build run (~3 min)
5. When it turns green → go to **Releases** → download `item-collector-1.0.0.jar`

---

## Install

Copy the JAR into `.minecraft/mods/` alongside:
- Fabric Loader ≥ 0.15.11
- Fabric API 0.116.9+1.21.1  (from https://modrinth.com/mod/fabric-api)

Launch Minecraft 1.21.1 with the Fabric profile.

---

## Add more target items

Edit `isTargetItem()` in `src/main/java/com/example/ItemCollectorMod.java`:

```java
private boolean isTargetItem(Item item) {
    return item == Items.DIAMOND
        || item == Items.EMERALD
        || item == Items.GOLD_INGOT
        // add more items here, e.g.:
        || item == Items.NETHER_STAR;
}
```

Push the change — GitHub Actions builds a new JAR automatically.
