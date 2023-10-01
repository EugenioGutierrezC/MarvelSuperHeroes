package com.eugenio.marvelsuperheroes.superheroslist.domain.mapper

import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.URL

class CharactersMapperTest {

    private lateinit var charactersMapper: CharactersMapper

    @Before
    fun setup() {
        charactersMapper = CharactersMapper()
    }

    @Test
    fun `test mapToViewItem`() {
        val character = mockCharacterResult()

        val expectedViewItem = CharacterViewItem(
            id = 123,
            name = "Iron Man",
            comics = 50,
            thumbnail = URL("https://path.to/image.jpg")
        )

        val mappedItem = charactersMapper.mapToViewItem(character)

        assertEquals(expectedViewItem, mappedItem)
    }

    @Test
    fun `test mapToViewItems`() {
        val characters = listOf(
            mockCharacterResult(),
            mockCharacterResult()
        )

        val expectedViewItems = listOf(
            CharacterViewItem(
                id = 123,
                name = "Iron Man",
                comics = 50,
                thumbnail = URL("https://path.to/image.jpg")
            ),
            CharacterViewItem(
                id = 123,
                name = "Iron Man",
                comics = 50,
                thumbnail = URL("https://path.to/image.jpg")
            ),
        )

        val mappedItems = charactersMapper.mapToViewItems(characters)

        assertEquals(expectedViewItems, mappedItems)
    }
}