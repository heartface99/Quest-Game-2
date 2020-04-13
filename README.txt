The Quest of Legends
Date: 4/13/2020

Min Jia Liu
BU ID: U54689134

Eunice Choi
BU ID: U75265203


1. how to run
On the terminal, just run these two commands:
	javac TheQuest.java
	java TheQuest



2. About each class:
TheQuest- this is the driver class, starts the actual game

Play: Intialize the game with introductions, and deals with user inputs along with type checking to makes sure input is correct. Base on input, it will call functions to do corresponding action.
	
	Subclasses of Play include:
		PlayQuestOfLegends- This class represents the logic of the Quest of legends game. It shares some qualities with the regular Quest game, which is why it extends it.
 

Board: The base class for the playing board, it is made up of tiles and contains functions for accessing the board and moving

	Subclasses of Board include:
		NexusBoard- This class represents the board for the game quest of legends, which shares properties with the regular board for quest.

Item- Has name, cost,level requirement as attribute. 

	Subclasses of Item that holds attribute that related to each of them 
		Weapon- had the attribute damage
		Armor- has the damage_reduction attribute
		Potion- has attribute of str,agi,dex,mana,hp, number of potion, and temp increase. The temp increase decides if the stats are for temporary increase of a hero or not.
		Spell-damage,mana_cost,spell_type(this will affect the attribute listed afte it), damage_reduction, dodge_reduction, defense_reduction 

Character_Monster: attribute that is relevant to both monster and hero such as hp,mana, max hp,max mana, and level and name.
	Subclass of Character_Monster:
		Monster: has the attribute related to Monster(dodge_chance,defense,damage etc)
		Character: Each character has attribute related to the character stats.Characters have their own collections of weapon, armor, and potion and spell.
			Charcter can only equip ONE wepeaon, and armor and spell at a time. They can as many potions they want when not in battle. 
			
			Subclass of Character: Warrior, Sorcerer, Paladin. They have their own ratio of stats which will affect how the stats are increased during leveling up.


MainMarket: Initialize all the items that can be sold in the market place. 

Monsterlist: Intitialize all the monsters that exists, and also create list of the monster that heros can fight. 

Hero_list: Intitilaize all the heros that a player can choose. Also have functions that act upon list of heros, such as resetting hero stats after a battle, what to do when a list of hero 
	complete a battle.

Player: has a name, piece representing the Player (which is O in this setting).Has a list of character that the player has picked in the beginning.

Tile: Makes up the board. Has row, col, and representation character. M is for market, X for unpassable terrain, no letter for empty tile.

SellableBuyable: An interface that ndicates/enforces the ability of items to be bought and sold (that the item is able to perform transactions)

HeroFightAbility: An interface that enforces the heros to have some fight-specific methods

MonsterFightAbility: An interface that enforces the monster to have some fight-specific methods