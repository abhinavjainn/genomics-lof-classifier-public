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

#### 1.1.2 ELGH (East London Genes and Health) data
This data cannot be made public due to restrcited access. VEP annotations added to the data are publicly available using Ensembl browser [here](https://www.ensembl.org/Tools/VEP).
