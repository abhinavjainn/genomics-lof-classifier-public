# genomics-lof-classifier-public
Classification of genetic loss-of-function variants using machine learning

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

#### 1.2.2 Part-2 Combine gnomAD and ELGH data, CADD
Python notebook is [here](https://github.com/abhinavjainn/genomics-lof-classifier-public/tree/main/pre-processing). VEP annotations dowloaded from Ensembl VEP, available [here](https://drive.google.com/file/d/1FDlkUqxFhF8P_TbbyQlK8s8hA3VkWWjy/view?usp=sharing). Processsed using Google Colab and Google Drive. Rest of the data of this part cannot be published.


