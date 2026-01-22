package com.example.yugioh.viewmodel

import com.example.yugioh.R
import com.example.yugioh.model.YuGiOhCard

object CardRepository {

    fun getCardList(): List<YuGiOhCard> = listOf(
        YuGiOhCard(
            index = "bond-60709218",
            name = "Bond Between Teacher and Student",
            type = "Spell Card",
            attack = null,
            defense = null,
            description = "If you control \"Dark Magician\": Special Summon 1 \"Dark Magician Girl\" from your hand, Deck, or GY, then you can Set certain related Spell cards from your Deck. You can only activate 1 per turn.",
            image = R.drawable.bond
        ),
        YuGiOhCard(
            index = "penguin-48531733",
            name = "Bolt Penguin",
            type = "Normal Monster (Thunder)",
            attack = 1100,
            defense = 800,
            description = "With each arm forming an electric whip, this monster paralyzes its enemies with electric shocks.",
            image = R.drawable.penguin
        ),
        YuGiOhCard(
            index = "caracol-12146024",
            name = "Bolt Escargot",
            type = "Normal Monster (Thunder)",
            attack = 1400,
            defense = 1500,
            description = "After rendering an opponent immobile by spitting a sticky goo, this monster closes in for the attack.",
            image = R.drawable.caracol
        ),
        YuGiOhCard(
            index = "laboratorio-6890729",
            name = "Bonding - DHO",
            type = "Trap Card",
            attack = null,
            defense = null,
            description = "Shuffle specific cards from your hand/GY into the Deck; Special Summon certain Water Dragon cards. (Effect summarized)",
            image = R.drawable.laboratorio
        ),
        YuGiOhCard(
            index = "esqueleto-25784595",
            name = "Bone Archfiend",
            type = "Effect Monster (Fiend)",
            attack = 1800,
            defense = 0,
            description = "If this card is in your hand or GY: You can send 1 other card from your hand or field to the GY; Special Summon this card.",
            image = R.drawable.esqueleto
        ),
        YuGiOhCard(
            index = "bongos-47778083",
            name = "Bone Temple Block",
            type = "Trap Card",
            attack = null,
            defense = null,
            description = "Discard 1 card. Special Summon 1 Level 4 or lower monster from your opponent's Graveyard to your side of the field.",
            image = R.drawable.bongos
        ),
        YuGiOhCard(
            index = "fuego-59834564",
            name = "Bonfire Colossus",
            type = "Effect Monster (Beast-Warrior)",
            attack = 2600,
            defense = 200,
            description = "If you control a FIRE monster, you can Special Summon this card (from your hand). When Special Summoned: target 2 FIRE monsters you control; destroy those targets.",
            image = R.drawable.fuego
        ),
        YuGiOhCard(
            index = "nene-263926",
            name = "Bonze Alone",
            type = "Effect Monster (Fairy)",
            attack = 100,
            defense = 100,
            description = "Cannot be Normal or Special Summoned if you control a monster. You can Special Summon this card (from your hand) in Attack Position.",
            image = R.drawable.nene
        ),
        YuGiOhCard(
            index = "e-24425055",
            name = "Booby Trap E",
            type = "Trap Card",
            attack = null,
            defense = null,
            description = "Discard 1 card; Set 1 Continuous Trap Card from your hand or Graveyard to your field. That Set card can be activated during this turn.",
            image = R.drawable.e
        ),
        YuGiOhCard(
            index = "rio-96704974",
            name = "Boogie Trap",
            type = "Spell Card",
            attack = null,
            defense = null,
            description = "Discard 2 cards, then target 1 Trap in your GY; Set that target. It can be activated this turn.",
            image = R.drawable.rio
        ),
        YuGiOhCard(
            index = "libro-35480699",
            name = "Book of Eclipse",
            type = "Quick-Play Spell",
            attack = null,
            defense = null,
            description = "Change all face-up monsters on the field to face-down Defense Position. During the End Phase of this turn, change as many face-down Defense Position monsters your opponent controls as possible to face-up Defense Position; then your opponent draws cards accordingly.",
            image = R.drawable.libro
        ),
        YuGiOhCard(
            index = "prisionero-89558090",
            name = "Dark Prisoner",
            type = "Normal Monster (Fiend)",
            attack = 600,
            defense = 1000,
            description = "This monster bends light to hide its image from the eyes of opponents.",
            image = R.drawable.prisionero
        )
    )
}