package ru.sber.oop

import kotlin.random.Random

open class Room(var name: String, var size: Int) {

    protected open val dangerLevel = 5
    val monster: Monster = Goblin(
        "Abaddon",
        "Abaddon, the Lord of Avernus. \tThe Font of Avernus is the source of a family's strength," +
                " a crack in primal stones from which vapors of prophetic power have issued for generations. " +
                "Each newborn of the cavernous House Avernus is bathed in the black mist, and by this baptism t" +
                "hey are given an innate connection to the mystic energies of the land. They grow up believing" +
                " themselves fierce protectors of their lineal traditions, the customs of the realm—but what they " +
                "really are protecting is the Font itself. And the motives of the mist are unclear.",
        250,
        "Wizard"
    )

    constructor(name: String) : this(name, 100) {
    }

    open fun description() = "Room: $name"

    open fun load() {
        println("Welcome to \"$name\"")
        monster.getSalutation()
    }

}

class TownSquare : Room("Town Square", 1000) {
    final override fun load() {
        println("Welcome to $name")
    }

    override val dangerLevel = super.dangerLevel - 3
}

// this is out of homework

fun startGame(room: Room, player: Player) {
    val monster = room.monster
    room.load()
    while (monster.healthPoints > 0 && player.healthPoints > 0) {
        val chooseAttacker = Random(System.nanoTime()).nextInt(0, 2)
        if (chooseAttacker == 1)
            println("${player.name} attacked to ${monster.name}, damage roll = ${player.attack(monster)}")
        else
            println("${monster.name} attacked to ${player.name}, damage roll = ${monster.attack(player)}")
        if (player.healthPoints < 0)
            player.healthPoints = 0
        if (monster.healthPoints < 0)
            monster.healthPoints = 0
        println("${player.name}: health = ${player.healthPoints}")
        println("${monster.name}: health = ${monster.healthPoints}")
        println("---------------------------------------------")
        if (monster.healthPoints == 0)
            println("${monster.name} is dead, win ${player.name}")
        if (player.healthPoints == 0)
            println("${player.name} is dead, win ${monster.name}")
        Thread.sleep(2000)
    }
}

fun main() {
    val room = Room("Meeting with a monster")
    val player = Player("Kunkka", false, 200, "Admiral")
    startGame(room, player)
}
