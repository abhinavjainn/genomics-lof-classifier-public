# LOF Classifier - public repository
Classification of the loss-of-function genetic variants into Benign, Pathogenic and Likely pathogenic classes using Machine Learning.


# Predictions using the final XGBoost model
[Prediction template for features](https://drive.google.com/file/d/1zJHIA_zAdgbdzRv8iB5GbU7pBynJH4Hk/view?usp=sharing), [Prediciton template for true labels (optional)](https://drive.google.com/file/d/1zJHIA_zAdgbdzRv8iB5GbU7pBynJH4Hk/view?usp=sharing).


Prediction pipeline has two steps: 1. Preprocess the features and if available, true labels. 2. Load the model and make predictions. Python notebooks are available [here](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/prediction-using-trained-model).

# Dataset creation, pre-processing and modelling details

## 1. Dataset

### 1.1 Extraction

Data has been sourced from three respositories [gnomAD](https://gnomad.broadinstitute.org/), [Ensembl](https://www.ensembl.org/) and [ELGH](https://www.genesandhealth.org/).

#### 1.1.1 Data extraction from gnomAD

##### 1.1.1.1 LoF Curation Results
Gene ids of the interest were exracted by combining the different LoF curation results avalable [here](https://gnomad.broadinstitute.org/downloads) and then filtering out everything that is not "lof".

##### 1.1.1.2 Variant extraction
Java source code for extraction is available [here](https://github.com/abhinavjainn/genomics-lof-classifier-public/blob/main/gnomad-var-extraction/src/test/java/gnomad/ExtractVariants.java) . Chrome driver is required to be downloaded and its path to be updated in the source code before running the code. Also, the path to the input file needs to be updated. Input file with Gene Ids used for the project is available [here](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/gnomad-var-extraction/Input).
Combined output of the extraction is available [here](https://drive.google.com/file/d/1I-UJEMG9mfN8uDdEW7ModST08XzKhr5l/view?usp=sharing).

##### 1.1.1.3 Constraints
Data downloaded from [gnmoAD protal](https://gnomad-public-us-east-1.s3.amazonaws.com/release/2.1.1/constraint/gnomad.v2.1.1.lof_metrics.by_transcript.txt.bgz).

#### 1.1.2 ELGH (East London Genes and Health) data
This data cannot be made public due to restrcited access. VEP annotations added to the data are publicly available using Ensembl browser [here](https://www.ensembl.org/Tools/VEP).

### 1.2 Data Cleaning and Preprocessing

#### 1.2.1 Part-1 gnomAD Variants and Constraint metric
Python notebook is available [here](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/pre-processing). Processsed using Google Colab and Google Drive.
Output is availabe [here](https://drive.google.com/file/d/1cVzB7YJRDjEwNoiE3KUMYLBXnM2MC2cu/view?usp=sharing).

#### 1.2.2 Part-2 Combine gnomAD and ELGH data
Python notebook is [here](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/pre-processing). VEP annotations dowloaded from Ensembl VEP, available [here](https://drive.google.com/file/d/1FDlkUqxFhF8P_TbbyQlK8s8hA3VkWWjy/view?usp=sharing). Processsed using Google Colab and Google Drive. Rest of the data of this part cannot be published.

#### 1.2.3 Part-3 Save test data. SMOTE oversampling.
[Python notebook](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/pre-processing)

#### 1.2.4 Part-4 Perform PCA and save data for training.
[Python notebook](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/pre-processing),
[Training features/Principal components](https://drive.google.com/file/d/1-3nHk_qIOyUEUntlAM4dhxvjLFzRDShM/view?usp=sharing),
[Labels](https://drive.google.com/file/d/1--WbOIeLzNkpKEhboXJAsh7ARBzwWBQp/view?usp=sharing) 


#### 1.2.5 Part-5 CADD Annotations
[Python notebook](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/pre-processing),
Processsed using Google Colab Pro (high capacity RAM) and Google Drive.
CADD annotations downloaded from [Washingtom Uni. portal](https://cadd.gs.washington.edu/download). Indels donwload [link](https://kircherlab.bihealth.org/download/CADD/v1.6/GRCh37/gnomad.genomes.r2.1.1.indel.tsv.gz). SNVs data split in two parts available here: [Part1](https://drive.google.com/file/d/1uxVQQR2IOwZSg9gqzCsbe5lsOiLkue3k/view?usp=sharing), [Part2](https://drive.google.com/file/d/1Y8ZMeJIUotMn6BF_AXTeCyOmVRSPvc0p/view?usp=sharing).
Since annotation were found only for a few variants, these annotations were discarded and not used for modelling.

## 2. Model Training, Testing and Evaluation

### 2.1 Original version of data
[Python notebook](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/model-training-testing-evaluation) with training, testing and evaluation results.

### 2.2 SMOTE version of data
[Python notebook](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/model-training-testing-evaluation) with training, testing and evaluation results.

### 2.3 PCA version of data
[Python notebook](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/model-training-testing-evaluation) with training, testing and evaluation results.
