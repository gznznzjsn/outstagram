# Outstagram

## Description

Demo project, clone of Instagram.

## Nodes

* Post (POST) (place, photos, description, tags)
* Account (ACCOUNT) (name, description, privacy, date of creation)
* Place (PLACE) (name)
* Tag (TAG) (name)

## Relationships

* Publication (PUBLISHED) (date, pinned)
* Like (LIKED) (date)
* Placement (PLACED) (date)
* Comment (COMMENTED) (date, comment)
* Subscription (SUBSCRIBED_TO)
* Tagging (TAGGED_WITH)

## TODO

* Tags and places has unique names - constraint
* Constraints for properties
* Constraints for relationships
* Create post
* Like post
* Comment post
* Get all comments on post
* Get all posts of account
* Change account privacy

## For investigation
* Constraint on type array (Post.photos)
* Change createdAt type to LocalDateTime
