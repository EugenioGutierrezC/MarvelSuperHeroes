package com.eugenio.marvelsuperheroes.superheroslist.domain.mapper

import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Comics
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Comics.Item
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Events
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Series
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Stories
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Thumbnail
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Url
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Events.Item as EventItem
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Series.Item as SeriesItem
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse.Data.Result.Stories.Item as StoriesItem

fun mockCharacterResult(): Result {
    return Result(
        comics = Comics(
            available = 50,
            collectionURI = "mockURI",
            items = listOf(
                Item(
                    name = "Mock Comic",
                    resourceURI = "mockResourceURI"
                )
            ),
            returned = 1
        ),
        description = "Mock Description",
        events = Events(
            available = 5,
            collectionURI = "mockEventURI",
            items = listOf(
                EventItem(
                    name = "Mock Event",
                    resourceURI = "mockResourceEventURI"
                )
            ),
            returned = 1
        ),
        id = 123,
        modified = "mockDate",
        name = "Iron Man",
        resourceURI = "mockResourceURI",
        series = Series(
            available = 5,
            collectionURI = "mockSeriesURI",
            items = listOf(
                SeriesItem(
                    name = "Mock Series",
                    resourceURI = "mockResourceSeriesURI"
                )
            ),
            returned = 1
        ),
        stories = Stories(
            available = 5,
            collectionURI = "mockStoriesURI",
            items = listOf(
                StoriesItem(
                    name = "Mock Story",
                    resourceURI = "mockResourceStoryURI",
                    type = "MockType"
                )
            ),
            returned = 1
        ),
        thumbnail = Thumbnail(
            extension = "jpg",
            path = "http://path.to/image"
        ),
        urls = listOf(
            Url(
                type = "mockType",
                url = "mockURL"
            )
        )
    )
}