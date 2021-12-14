import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import confusion_matrix, ConfusionMatrixDisplay, classification_report, f1_score
import pickle


def prepare_training(df_X, df_y, test_size=0.3):
    X = df_X.to_numpy()
    y = df_y.to_numpy()
    y = y.reshape(-1, )
    # Normalize the data
    scaler = StandardScaler()
    X = scaler.fit_transform(X)
    # Split between training and testing sets
    X_train, X_val, y_train, y_val = train_test_split(X, y, test_size=test_size)
    return X_train, X_val, y_train, y_val


def hparam_grid_search(model, param_grid, X_train, y_train, verbose=1):
    grid = GridSearchCV(model, param_grid, refit=True, verbose=verbose, n_jobs=-1)
    grid.fit(X_train, y_train)
    return grid.best_params_


def train_test_result(model, train=True, X_train=None, X_val=None, y_train=None,
                      y_val=None, class_names=None, train_f1=False, train_cm=False,
                      test=True, test_f1=True, test_cm=True, class_rep=True, test_ret=True):
    if test:
        if X_val is None or y_val is None:
            raise ValueError('Test set must be supplied when testing is required')

    if train:
        model.fit(X_train, y_train)
        y_train_pred = model.predict(X_train)
        print("Training Accuracy: ", np.mean(y_train_pred == y_train))
        if train_f1:
            f1 = f1_score(y_train, y_train_pred, average="weighted")
            print("Training F1-Score:", f1)

        if train_cm:
            display_confusion_matrix(model, y_train, y_train_pred, class_names, normalize=None, training=True)
            display_confusion_matrix(model, y_train, y_train_pred, class_names, normalize="true", training=True)

    if test:
        y_val_pred = model.predict(X_val)
        test_acc = np.mean(y_val_pred == y_val)
        print("Testing Accuracy: ", test_acc)
        if test_f1:
            f1 = f1_score(y_val, y_val_pred, average="weighted")
            print("Testing F1-Score:", f1)
        if test_cm:
            display_confusion_matrix(model, y_val, y_val_pred, class_names, normalize=None)
            display_confusion_matrix(model, y_val, y_val_pred, class_names, normalize="true")
        if class_rep:
            print(classification_report(y_val, y_val_pred))
        if test_ret:
            return test_acc if not test_f1 else test_acc, f1


def display_confusion_matrix(model, y_val, y_val_pred, class_names, normalize=None, training=False):
    cm = confusion_matrix(y_val, y_val_pred, labels=model.classes_, normalize=normalize)
    display = ConfusionMatrixDisplay(confusion_matrix=cm,
                                     display_labels=class_names)
    display.plot()
    plt.xticks(rotation=45)
    if normalize is None:
        if training:
            plt.title("Training Confusion Matrix - Raw")
        else:
            plt.title("Testing Confusion Matrix - Raw")
    else:
        if training:
            plt.title("Training Confusion Matrix - Normalized")
        else:
            plt.title("Testing Confusion Matrix - Normalized")
    plt.show()


def save_model(model, path):
    try:
        pickle.dump(model, open(path, 'wb'))
        print("Models saved successfully.")
    except:
        print("Error occurred while saving the models.")
