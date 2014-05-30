Online Recommender
------------------

Proof of concept implementation of recommender system.

## QuickStart

There is test class(OnlineRecommenderTest) which train data and check its recommendation.

## Implementation details

### Algorithm

Stochastic matrix factorization

## TODO

To make it serve recommendation result, we should

* calculate all item scores for given user
* take max scores

To make it scalable, some efforts are needed.

* item-feature vectors should reside in distributed storage.
  * should serve getWithDefault
  * should serve non-locking update with deltas