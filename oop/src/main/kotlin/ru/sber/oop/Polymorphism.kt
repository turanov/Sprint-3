package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = Random(System.nanoTime()).nextInt(0, 50)

    fun attack(opponent: Fightable): Int
}

class Player(
    val name: String,
    val isBlessed: Boolean,
    override var healthPoints: Int,
    override val powerType: String,
) : Fightable {

    override fun attack(opponent: Fightable): Int {
        val amountDamageRoll: Int = if (isBlessed) damageRoll * 2 else damageRoll
        opponent.healthPoints -= amountDamageRoll
        return amountDamageRoll
    }
}

abstract class Monster(val name: String, val description: String) : Fightable {

    override fun attack(opponent: Fightable): Int {
        opponent.healthPoints -= damageRoll
        return damageRoll
    }
}

class Goblin(
    _name: String,
    _description: String,
    override var healthPoints: Int,
    override val powerType: String,
) : Monster(_name, _description) {
    override val damageRoll: Int
        get() = super.damageRoll / 2
}

fun Monster.getSalutation() = println("$name: UaUaAaAa!!! You will die today!")
