package com.example.cred_assignment.domain.models

// --- First View ---

data class ContentModel(
    val firstViewContent: FirstViewContent,
    val secondViewContent: SecondViewContent,
    val thirdViewContent: ThirdViewContent
)
data class FirstViewContent(
    val openState: FirstViewOpenState,
    val closedState: FirstViewClosedState,
    val ctaText: String
)

data class FirstViewOpenState(
    val title: String,
    val subtitle: String,
    val card: CardData,
    val footer: String
)

data class FirstViewClosedState(
    val key1: String,
)

// --- Second View ---
data class SecondViewContent(
    val openState: SecondViewOpenState,
    val closedState: SecondViewClosedState,
    val ctaText: String
)

data class SecondViewOpenState(
    val title: String,
    val subtitle: String,
    val items: List<RepaymentOption>,
    val footer: String
)

data class SecondViewClosedState(
    val key1: String,
    val key2: String,
)

// --- Third View ---
data class ThirdViewContent(
    val openState: ThirdViewOpenState,
    val closedState: ThirdViewClosedState,
    val cta: String
)

data class ThirdViewOpenState(
    val title: String,
    val subtitle: String,
    val items: List<BankAccount>,
    val footer: String
)

data class ThirdViewClosedState(
    val body: Unit? = null,
)
// ---
data class CardData(
    val header: String,
    val description: String,
    val maxRange: Int,
    val minRange: Int
)

data class RepaymentOption(
    val emi: String,
    val duration: String,
    val title: String,
    val subtitle: String,
    val tag: String?
)

data class BankAccount(
    val icon: String?,
    val title: String,
    val subtitle: String
)