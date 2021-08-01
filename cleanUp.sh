#!/bin/bash
path=$1
cd $path
echo "Entering directory..."
cd Docs
echo "...Backing up the Urls..."
mv Urls.txt Urls.txt_bk
echo "Removing content files....."
rm -rf *.txt
echo "....Restoring Urls...."
mv Urls.txt_bk Urls.txt
