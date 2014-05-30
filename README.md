Online Recommender
------------------

Proof of concept implementation of recommender system.

## QuickStart

There are some example and test cases to show that it's working.

### Test class

There is test class(OnlineRecommenderTest) which train data and check its recommendation.
Run with `sbt test` to check it works properly.

### Example program

class Main works with MovieLens data to predict user-item preferences.

1. Download ml-1m.zip from [MovieLens](http://grouplens.org/datasets/movielens/)
2. unzip and move ml-1m directory to project root.
3. run class Main via `sbt run` or other.
4. Check Total Diff and change iteration count to get model better or worse.
(more you train, better the model)

## Implementation details

### Algorithm

Stochastic matrix factorization

## TODO

To create recommendation list for given user, we should

* select all items except user already scored(how can we deal with implicit feedback items?)
* predict item scores
* take only max scores(recommend using heap)

To make it scalable, some efforts are needed.

* reimplement FeatureMap to use NoSQL databases.
  * should serve getOption
  * should serve set
  * prefer if it serves add for given vector without locking
