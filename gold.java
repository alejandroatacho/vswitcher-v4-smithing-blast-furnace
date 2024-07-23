int bankId = 26707;
int barDispenseId = 9092;
int conveyorId = 9100;
WorldPoint barWP = new WorldPoint(1940, 4962, 0);
WorldPoint bankWP = new WorldPoint(1948, 4957, 0);
WorldPoint beltWP = new WorldPoint(1942, 4967, 0);

int slots_available = 27;

int gold_id = 444;
int gold_bar_id = 2357;
int stamina_id=12631;
int ice_gloves_id=1580;
int goldsmith_gloves_id=776;

if (client.getEnergy() >= 20) {
  v.getWalking().turnRunningOn();
}


// Withdraw Gold
if (v.getWalking().nearPosition(bankWP, 0) && v.getInventory().amountInInventory(gold_id, 0, 0)){
	GameObject bankChest = v.getGameObject().findNearest(bankId);
	if (bankChest != null) {
		
	int bankChestSceneX = bankChest.getSceneMinLocation().getX();
    int bankChestSceneY = bankChest.getSceneMinLocation().getY();
    v.getInventory().equip(goldsmith_gloves_id);

	if(client.getEnergy()<=3000)

{
	v.invoke("Use","<col=ffff>Bank Chest",bankId,3,bankChestSceneX,bankChestSceneY,false);
	
v.getCallbacks().afterTicks(1, () -> {
			//v.getBank().deposit(gold_bar_id, slots_available);
             v.getBank().depositAll();
			 v.getCallbacks().afterTicks(1, () -> {
			v.getBank().withdraw(stamina_id,1);
			 v.getCallbacks().afterTicks(1, () -> {
			v.invoke("Drink","<col=ff9040>Stamina potion(1)</col>",9,1007,0,983043,false);
		//	v.getInventory().pot(Potion.STAMINA_POTION);
			});
			 });
			
});
//v.invoke("Close","",1,57,11,786434,false);
}

else{
		v.invoke("Use","<col=ffff>Bank Chest",bankId,3,bankChestSceneX,bankChestSceneY,false);
		v.getCallbacks().afterTicks(1, () -> {
			//v.getBank().deposit(gold_bar_id, slots_available);
             v.getBank().depositAll();
			 v.getCallbacks().afterTicks(1, () -> {
			v.getBank().withdraw(gold_id, slots_available);
            v.getBank().withdraw(ice_gloves_id,1);
;			 });
		//	v.invoke("Close","",1,57,11,786434,false);
			});
			v.invoke("Close","",1,57,11,786434,false);
	}
	}
// Run to belt
System.out.println(client.getEnergy());
}else if (v.getWalking().nearPosition(bankWP, 0) && v.getInventory().amountInInventory(gold_id, slots_available, slots_available)){
	v.getWalking().walkR(beltWP, 0);
}else if (v.getWalking().nearPosition(beltWP, 0) && v.getInventory().amountInInventory(gold_id, slots_available, slots_available)){
	GameObject belt = v.getGameObject().findNearest(conveyorId);
	if (belt != null) {
		int beltSceneX = belt.getSceneMinLocation().getX();
    int beltSceneY = belt.getSceneMinLocation().getY();
		v.invoke("Put-ore-on", "Converyor Belt", conveyorId, 3, beltSceneX, beltSceneY, false);
	}

// Run to bar dispenser
}else if (v.getWalking().nearPosition(beltWP, 0) && v.getInventory().amountInInventory(gold_id, 0, 0)){
	v.getWalking().walkR(barWP, 0);
// Withdraw bars
}else if (v.getWalking().nearPosition(barWP, 0)  && !v.getInventory().amountInInventory(gold_bar_id, slots_available, slots_available)){
	GameObject bar = v.getGameObject().findNearest(barDispenseId);
	if (bar != null) {
		int barSceneX = bar.getSceneMinLocation().getX();
    int barSceneY = bar.getSceneMinLocation().getY();
    v.getInventory().equip(ice_gloves_id);
    v.invoke("Check", "Bar Dispenser", barDispenseId, 3, barSceneX, barSceneY, false);
    v.getCallbacks().afterTicks(3, () -> {
			v.invoke("Take","Gold Bar",1,57,-1,17694734,false);
		});
	}
	
}else if (v.getWalking().nearPosition(barWP, 0) && v.getInventory().amountInInventory(gold_bar_id, slots_available, slots_available)){
	v.getWalking().walkR(bankWP, 0);
}