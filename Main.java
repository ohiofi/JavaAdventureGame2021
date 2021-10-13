/*
List of other classes and methods that we need to create

Room
 - NoArgsConstructor
 - Room(String _name)
 - getCharacter()
 - getItem()
 - getLocationTo(String direction)
 - getName()
 - getPossibleDirections()
 - setCharacter(Npc character)
 - setDescription(String d)
 - setItem(Item i)
 - setName(String _name)
 - toString() // returns the description
 
Npc
 - NoArgsConstructor
 - Npc(String _name, String _description)
 - getName
 - getSpeech
 - setDescription(String d)
 - setName(String _name)
 - setSpeech
 - toString() // returns the description
 
Enemy extends Npc
 - NoArgsConstructor
 - Enemy(String _name, String _description)
 - String getAttackName()
 - int getHealth
 - int getMagicType()
 - void loseHealth(int h)
 - void setAttackName()

Item
 - NoArgsConstructor
 - Item(String _name, String _description)
 - getMagicType()
 - getName()
 - getStrength()
 - setDescription(String d)
 - setName(String _name)
 - isBroken()
 - toString() // returns the description
 - weaken()


Map:
|                |   billiardsRm    |   theBasement     |   masterBedroom   |
|    kitchen     |   diningHall     |   mainHallway     |   theStudy        |
|                |   ballroom       |   grandFoyer      |                   |
*/
import java.util.Scanner;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Random rng = new Random();

    // create the world

    // create rooms
    Room kitchen = new Room("Kitchen");
    Room diningHall = new Room("Dining Hall");
    Room ballroom = new Room("Ballroom");
    Room masterBedroom = new Room("Master Bedroom");
    Room grandFoyer = new Room("Grand Foyer");
    Room mainHallway = new Room("Main Hallway");
    Room theStudy = new Room("The Study");
    Room billiardsRm = new Room("The Billiards Room");
    Room theBasement = new Room("The Basement");

    // descriptions
    kitchen.setDescription("A dank and dirty room buzzing with flies.");
    diningHall.setDescription("A large room with ornate golden decorations on each wall.");
    ballroom.setDescription("A vast room with a shiny wooden floor. Huge candlesticks guard the entrance.");
    masterBedroom.setDescription("");
    grandFoyer.setDescription("The entrance to the dark mansion. Designed to astonish guests. The hallway is north and there is are large, double-doors to the west.");
    mainHallway.setDescription("");
    theStudy.setDescription("");
    billiardsRm.setDescription("");
    theBasement.setDescription("");

    // link rooms together. don't forget to link the rooms in both directions.
    grandFoyer.linkRoom(mainHallway, "north");
    mainHallway.linkRoom(grandFoyer, "south");

    grandFoyer.linkRoom(ballroom, "west");
    ballroom.linkRoom(grandFoyer, "east");

    ballroom.linkRoom(diningHall, "north");
    diningHall.linkRoom(ballroom, "south");

    kitchen.linkRoom(diningHall, "east");
    diningHall.linkRoom(kitchen, "west");

    diningHall.linkRoom(billiardsRm, "north");
    billiardsRm.linkRoom(diningHall, "south");

    diningHall.linkRoom(mainHallway, "east");
    mainHallway.linkRoom(diningHall, "west");

    mainHallway.linkRoom(theBasement, "north");
    theBasement.linkRoom(mainHallway, "south");

    mainHallway.linkRoom(theStudy, "east");
    theStudy.linkRoom(mainHallway, "west");

    theStudy.linkRoom(masterBedroom, "north");
    masterBedroom.linkRoom(theStudy, "south");

    billiardsRm.linkRoom(theBasement, "east");
    theBasement.linkRoom(billiardsRm, "west");

    theBasement.linkRoom(masterBedroom, "east");
    masterBedroom.linkRoom(theBasement, "west");

    // create characters
    // Ava
    Enemy ava = new Enemy("Ava", "A beautiful chicken");
    ava.setSpeech("Hello...");
    ava.setAttackName("PECK");
    if (rng.nextInt(2) == 0) {
      kitchen.setCharacter(ava);
    } else {
      billiardsRm.setCharacter(ava);
    }

    // Becky
    Enemy becky = new Enemy("Becky", "A wicked witch");
    becky.setSpeech("Yahaha! You found me!");
    ava.setAttackName("BAD BREATH");
    if (rng.nextInt(2) == 0) {
      theBasement.setCharacter(becky);
    } else {
      diningHall.setCharacter(becky);
    }

    // Catrina
    Npc catrina = new Npc("Catrina", "A friendly skeleton");
    catrina.setSpeech("Why hello there.");
    if (rng.nextInt(2) == 0) {
      mainHallway.setCharacter(catrina);
    } else {
      ballroom.setCharacter(catrina);
    }

    // Dave
    Enemy dave = new Enemy("Dave", "A smelly zombie");
    dave.setSpeech("Brrlgrh... rhrhl... brains...");
    ava.setAttackName("BITE");
    if (rng.nextInt(2) == 0) {
      masterBedroom.setCharacter(dave);
    } else {
      theStudy.setCharacter(dave);
    }

    // create items
    Item staff = new Item("staff", "A weird old wizard's staff");
    if (rng.nextInt(2) == 0) {
      kitchen.setItem(staff);
    } else {
      diningHall.setItem(staff);
    }

    Item sword = new Item("sword", "A well-decorated sword");
    if (rng.nextInt(2) == 0) {
      masterBedroom.setItem(sword);
    } else {
      theStudy.setItem(sword);
    }

    Item wand = new Item("wand", "A strange, glowing wand");
    if (rng.nextInt(2) == 0) {
      billiardsRoom.setItem(wand);
    } else {
      theBasement.setItem(wand);
    }

    // player variables
    int playerHealth = 100;
    int playerKickStrength = rng.nextInt(7) + 1;
    int playerPunchStrength = 9 - playerKickStrength;
    int enemiesDefeated = 0;
    Item backpack = null;
    Room currentRoom = grandFoyer;

    // the game loop
    while (true) {
      System.out.println("\n");
      Npc roomNpc = currentRoom.getCharacter();
      Item roomItem = currentRoom.getItem();
      // describe the current room
      System.out.println(currentRoom.toString());
      // check if there is a character and/or item in current room
      if (roomNpc != null) {
        System.out.println(roomNpc.toString());
      }
      if (roomItem != null) {
        System.out.println(roomItem.toString());
      }
      if (backpack != null) {
        System.out.println("You are holding " + backpack.toString());
      }
      // prompt
      System.out.print("WHAT NEXT? " + currentRoom.getPossibleDirections() + ", " + (currentRoom.getItem() == null ? "" : "take, ") + (currentRoom.getCharacter() == null ? "" : "talk, fight, ") + "or quit: ");
      String command = input.next();
      command = command.toLowerCase();

      if (command.equals("north") || command.equals("south") || command.equals("east") || command.equals("west")) {
        currentRoom = currentRoom.getLocationTo(command);
      } else if (command.equals("take")) {
        takeItem(currentRoom, backpack);
      } else if (command.equals("talk")) {
        if (roomNpc != null) {
          System.out.println(roomNpc.getSpeech());
        } else {
          System.out.println("There is nobody here to talk");
        }
      } else if (command.equals("fight")) {
        playerHealth = fight(input, roomNpc, backpack, playerHealth, playerPunchStrength, playerKickStrength);
      } else if (command.equals("quit")) {
        System.out.println("Thanks for playing");
        break;
      } else {
        System.out.println("I don't know how to " + command);
        System.out.println("Valid options include: " + currentRoom.getPossibleDirections() + ", " + (currentRoom.getItem() == null ? "" : "take, ") + (currentRoom.getCharacter() == null ? "" : "talk, fight, ") + "or quit: ");
      }
      if (playerHealth <= 0) {
        System.out.println("You died. Game over.");
        break;
      }
    } // close while loop
  } // close main method

  /*
      Pick up an item from the current room and put it in the backpack. If something is in the backpack, drop it in the room.
  */
  public static void takeItem(Room currentRoom, Item backpack) {
    if (backpack != null) {
      Item temp = backpack;
      backpack = roomItem;
      currentRoom.setItem(temp);
      System.out.println("You drop " + temp + " and pick up " + roomItem + ".");
    } else {
      // not holding anything right now
      backpack = roomItem;
      System.out.println("You pick up " + roomItem + ".");
      currentRoom.setItem(null);
    }
  }

  /*
      fight with an enemy. returns the new playerHealth.
  */
  public static void fight(Scanner input, Random rng, Npc currentNpc, Item backpack, int playerHealth, int playerPunchStrength, int playerKickStrength) {
    if (currentNpc == null) {
      System.out.println("There is nobody here to fight.");
      return playerHealth;
    }
    if (currentNpc instanceof Enemy == false) {
      System.out.println(currentNpc.getName() + " doesn't want to fight you.");
      return playerHealth;
    }
    Enemy e = (Enemy) currentNpc;
    while (playerHealth > 0) {
      System.out.print("FIGHT!!! p = punch, k = kick, r = run" + (backpack == null ? "" : ", x = use " + backpack.getName()) + ": ");
      String command = input.next();
      command = command.toLowerCase();
      if (command.equals("r")) {
        if (rng.nextInt(2) == 0) {
          System.out.println("You escaped... but " + e.getName() + " hits you as you run away...");
          playerHealth = playerLoseHealth(rng, e, playerHealth);
          return playerHealth;
        } else {
          System.out.println("Oof! Tried to run away, but could not escape!");
        }
      }
      enemyLoseHealth(rng, command, e, playerPunchStrength, playerKickStrength, backpack);
      if (e.getHealth() > 0) {
        // player lose health
        playerHealth = playerLoseHealth(rng, e, playerHealth);
      } else {
        System.out.println(e.getName() + " fainted! You won the fight!");
        return playerHealth;
      }
    }
    return playerHealth;
  }

  public static int playerLoseHealth(Random rng, Enemy e, int playerHealth) {
    int attack = rng.nextInt(6) + rng.nextInt(6) + 2;
    System.out.println(" > > > " + e.getName() + " uses " + e.getAttackName());
    System.out.println(" > > > -" + attack + " HP");
    playerHealth -= attack;
    System.out.println("You have " + playerHealth + " HP remaining");
    return playerHealth;
  }

  public static void enemyLoseHealth(Random rng, String command, Enemy e, int playerPunchStrength, int playerKickStrength, Item backpack) {
    int attack = 0;
    if (command.equals("p")) {
      System.out.println("You used PUNCH");
      attack = rng.nextInt(playerPunchStrength) + rng.nextInt(playerPunchStrength) + 1;
      if (attack >= 12) {
        System.out.println("It's super effective!");
      }

    } else if (command.equals("k")) {
      System.out.println("You used KICK");
      attack = rng.nextInt(playerKickStrength) + rng.nextInt(playerKickStrength) + 1;
      if (attack >= 12) {
        System.out.println("It's super effective!");
      }
    } else if (command.equals("x") && backpack != null) {
      System.out.println("You used " + backpack.getName().toUpperCase());
      attack = rng.nextInt(backpack.getStrength()) + rng.nextInt(backpack.getStrength()) + rng.nextInt(backpack.getStrength()) + 1;
      if (backpack.getMagicType().equals(e.getMagicType())) {
        attack = attack * 3;
      }
      if (attack >= 12) {
        System.out.println("It's super effective!");
      }
      backpack.weaken();
      if (backpack.isBroken()) {
        System.out.println("Oh no! Your " + backpack.getName() + " broke!");
        backpack = null;
      }
    } else {
      System.out.println("Sorry, I don't know how to " + command);
      System.out.print("Valid options: p, k, r" + (backpack == null ? "" : ", x = use " + backpack.getName()) + "");
      return;
    }
    System.out.println(e.getName() + " -" + attack + " HP");
    e.loseHealth(attack);
  }
} // close the class
