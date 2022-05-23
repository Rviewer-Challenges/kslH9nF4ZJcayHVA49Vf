package com.rumosoft.library_simpsons_api.data.local

import com.rumosoft.library_simpsons_api.data.model.CardDto
import javax.inject.Inject

class SimpsonsLocalDataSource @Inject constructor() {
    fun fetchCards(numCards: Int): List<CardDto> {
        return allImages().shuffled().take(numCards)
    }

    private fun allImages(): List<CardDto> {
        var id = 0
        return listOf(
            "Apu Nahasapeemapetilon" to "apu_nahasapeemapetilon",
            "Barney Gumble" to "barney_gumble",
            "Bart Simpson" to "bart_simpson",
            "Charles Montgomery Burns" to "charles_montgomery_burns",
            "Clancy Wiggum" to "clancy_wiggum",
            "Dr Hibbert" to "dr_hibbert",
            "Edna Krabappel" to "edna_krabappel",
            "Grandpa Simpson" to "grandpa_simpson",
            "Groundskeeper Willie" to "groundskeeper_willie",
            "Homer Simpson" to "homer_simpson",
            "Itchy" to "itchy",
            "Krusty The Clown" to "krusty_the_clown",
            "Lisa Simpson" to "lisa_simpson",
            "Maggie Simpson" to "maggie_simpson",
            "Marge Simpson" to "marge_simpson",
            "Maude Flanders" to "maude_flanders",
            "Mayor Guimby" to "mayor_quimby",
            "Milhouse Van Houten" to "milhouse_van_houten",
            "Moe Szyslak" to "moe_szyslak",
            "Ned Flanders" to "ned_flanders",
            "Nelson Muntz" to "nelson_muntz",
            "Otto Mans" to "otto_mans",
            "Principal Skinner" to "principal_skinner",
            "Ralph Wiggum" to "ralph_wiggum",
            "Santa's Little Helper" to "santas_little_helper",
            "Scratchy" to "scratchy",
            "Selma Bouvier" to "selma_bouvier",
            "Sideshow Bob" to "sideshow_bob",
            "Sideshow Mel" to "sideshow_mel",
            "Snowball" to "snowball",
            "Todd Flanders" to "todd_flanders",
            "Waylon Smithers" to "waylon_smithers",
        ).map {
            id++
            CardDto(
                characterId = id,
                name = it.first,
                imageUrl = it.second
            )
        }
    }
}