# Memory game

Simple app containing in a matching cards game.
I've chosen a cartoons theme mostly because it's a theme that I like but also because these images
are perfect in vector format so that they can be stored as vector drawables taking few space
and the size of the app remains small and can be used offline.

By the way, I've followed all the instructions but it says the cards should remain uncovered, 
I've preferred to make disappear the matches so that the user has a better vision of what's left,
or at least that's my idea. I hope this doesn't matter.
It also doesn't specify what to do after the two cards are flipped, I was between flipping them 
down after some time has passed but then decided it was better to flip them down when the next 
movement is done, I think both ways should be ok as both seem to follow the given instructions.
In a normal environment with more interaction I would checked with the one in charge (usually a PO)
what should be done before starting but as it's just a game that does not seem critical.

## Modules
I tried to create different modules so that the app is about memory game and matchingcards 
is just a feature of it but it could have multiple different features.

These modules are:
- feature-matchingcards: Contains the code of the matching cards game (without the datasource)
- library-simpsons-api: The data source to get info from the simpsons. It doesn't call any network 
endpoint as for this exercise doesn't seem necessary, but it could in case it should be extended 
and get the data from an online endpoint.
- library-components: it's idea is to have components that can be reused in 
different features of the app.
- library-core-navigation: is a kotlin lib that only contains an interface used in navigation. 
All feature modules include this.
- library-tests: Contains utilities to test coroutines and also some testing libraries using for 
jvm unit tests.

## Resources

FlipCard code is from: https://fvilarino.medium.com/creating-a-rotating-card-in-jetpack-compose-ba94c7dd76fb
Most of the Simpsons images (to transform to vector drawables): https://all-free-download.com/free-vector/simpsons-svg.html

## Trick
One simple way of being able to quickly win the game while testing is to comment the .shuffled() 
in MatchingCardsRepositoryImpl.kt so that the cards and their matches are always in the same 
positions.

## Video playing the game
https://www.youtube.com/shorts/LD6-VDvAaro
