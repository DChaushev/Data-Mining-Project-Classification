# Data-Mining-Project-Classification
Final project for university course "Data Mining"

Implementation of Naïve Bayes Classifier for movie reviews classification. We are determining if a given review is positive or negative for the movie.

The data we are using is from Stanford University, containing 25 000 reviews for training and 25 000 reviews for testing (12 500 positive and 12 500 negative each).</br>
source: http://ai.stanford.edu/~amaas/data/sentiment/

This is a the most basic classifier, considering each word as an individual, completely excluded from any context.

With that classifier and that data, we reached these results:

| Measure:   | Value:             |
|------------|--------------------|
| Accuracy:  | 70.332%            |
| Precision: | 64.75129142724477% |
| Recall:    | 89.248%            |
| measure:   | 75.05129671364661  |

------
**Future improvements:**</br>
It will be interesting to see the result if we take pair of words in mind and also exclude some words like 'I', 'the', 'a' and so on.

Currently, the review *“I like the movie.”* will be classified as negative. Each word from it is considered separately with different probability for negative and positive.</br>
If we remove the redundant words and have in mind the pairs, we will be left with "like", "like movie", "movie" ("movie" can be redundant as well. Should be tested.), which might have a higher chance of being classified as positve.
