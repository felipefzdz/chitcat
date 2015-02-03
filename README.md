[![Build Status](https://travis-ci.org/olid16/chitcat.svg)](https://travis-ci.org/olid16/chitcat)
[![Coverage Status](https://coveralls.io/repos/olid16/chitcat/badge.svg)](https://coveralls.io/r/olid16/chitcat)

# CHITCAT

This pet project implements a console-based Twitter clone. Chitcat is a wordplay using chit-chat and cat (an animal is mandatory in a Twitter clone).

![image](http://seriouscat.com/serious_cat.jpg)

## USAGE

1. Clone this repo
2. mvn package (you should have Java 8 installed, and maven pointing to that version)
3. java -jar chitcat/target/chitcat-1.0-SNAPSHOT-capsule-fat.jar

## ARCHITECTURE

I've followed some DDD ideas. As you can see domain code is completely isolated from its adapters. In this case user facing is a simple CLI, but if we would like to plug a REST interface it would be really easy as the domain is just expecting and returning pure domain objects. The other adapter is storing objects in memory, but, again, it should be trivial to provide another collection implementation that goes to DB or whatever.

I've BDDed the app from domain, so acceptance tests should be really fast. Here I agree with Uncle Bob, about mocking external dependencies, so you're sure that with acceptance testing you're just testing the business logic that the client requires, and not the integration with third parties.

The most polemic point for the reader in my architecture will be likely the data model. Being a chit like a tweet, one could think that the chit should be the central entity of my app. But reading closely the spec, we can see that the we don't care about chit lifecycle (so, it should be inmmutable) and that we don't want to query them. That means that Chits are values in my app, and the entities could be User, Timeline and Wall. That would change if we'd have user stories around chits like sharing chits or updating/deleting them, but I didn't want to assume any possible future, and just focus in the existing features.

That adds some serious denormalization of our data. Now, the chits are duplicated into Walls or Timelines. If we assume that this app is going to face massive amounts of chits, we should measure and decide which is the best strategy, the trade-offs for every approach would be:

1. Denormalisated chits: slow writes, more space needed and faster reads.
2. Normalisated chits: the opposite :)

What you can see, without needing measures, is that how the logic is structured changes pretty much. The business logic moves mainly to Create Chit action and into the in-memory stores. Even though this app has a simple architecture, we could think in Create Chit as an example about using domain events. When Create Chit domain event happens some subscribers react to that. For instance, followers of that user would be subscribers that will react performing an update on its own walls. 
