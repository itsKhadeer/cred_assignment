package com.example.cred_assignment.domain.util

import com.example.cred_assignment.data.datasource.api.entity.Card
import com.example.cred_assignment.data.datasource.api.entity.Content
import com.example.cred_assignment.data.datasource.api.entity.Item
import com.example.cred_assignment.data.datasource.api.entity.OpenStateBodyItem
import com.example.cred_assignment.domain.models.*


fun Content.toContentModelDomain(): ContentModel {
    return ContentModel(
        firstViewContent = items[0].toFirstViewDomain(),
        secondViewContent = items[1].toSecondViewDomain(),
        thirdViewContent = items[2].toThirdViewDomain()
    )
}
// --- First View Mapper ---

fun Item.toFirstViewDomain(): FirstViewContent {
    return FirstViewContent(
        openState = FirstViewOpenState(
            title = openState.body.title,
            subtitle = openState.body.subtitle,
            card = openState.body.card!!.toDomain(), // Assuming card is always present in the first view
            footer = openState.body.footer
        ),
        closedState = FirstViewClosedState(
            key1 = closedState.body.key1 ?: "",
        ),
        ctaText = ctaText
    )
}

// --- Second View Mapper ---

fun Item.toSecondViewDomain(): SecondViewContent {
    return SecondViewContent(
        openState = SecondViewOpenState(
            title = openState.body.title,
            subtitle = openState.body.subtitle,
            items = openState.body.items!!.map { it.toRepaymentOptionDomain() }, // Assuming items are always present in the second view
            footer = openState.body.footer
        ),
        closedState = SecondViewClosedState(
            key1 = closedState.body.key1 ?: "",
            key2 = closedState.body.key2 ?: "",
        ),
        ctaText = ctaText
    )
}

// --- Third View Mapper ---

fun Item.toThirdViewDomain(): ThirdViewContent {
    return ThirdViewContent(
        openState = ThirdViewOpenState(
            title = openState.body.title,
            subtitle = openState.body.subtitle,
            items = openState.body.items!!.map { it.toBankAccountDomain() }, // Assuming items are always present in the third view
            footer = openState.body.footer
        ),
        closedState = ThirdViewClosedState(),
        cta = ctaText
    )
}

// --- Shared Mapper Functions ---

fun Card.toDomain(): CardData {
    return CardData(
        header = header,
        description = description,
        maxRange = maxRange,
        minRange = minRange
    )
}

fun OpenStateBodyItem.toRepaymentOptionDomain(): RepaymentOption {
    return RepaymentOption(
        emi = emi ?: "",
        duration = duration ?: "",
        title = title,
        subtitle = subtitle ?: "",
        tag = tag
    )
}

fun OpenStateBodyItem.toBankAccountDomain(): BankAccount {
    return BankAccount(
        icon = "", // You'll need to handle icon mapping here
        title = title,
        subtitle = subtitle ?: ""
    )
}