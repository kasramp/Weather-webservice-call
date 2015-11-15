#!/bin/bash
INSTALL_FOLDER='.getweather/'
INSTALL_PATH="$HOME/$INSTALL_FOLDER"
SRC_FOLDER="JarFile"
ALIAS="alias weather='java -jar $INSTALL_PATH""WeatherWebserivceCall.jar'"
BASH_RC_PATH="$HOME/.bashrc"
mkdir -p "$INSTALL_PATH"
cd $SRC_FOLDER
cp *.* "$INSTALL_PATH"
grep -q -F "$ALIAS" "$BASH_RC_PATH" || echo "$ALIAS" >> "$BASH_RC_PATH"
#echo "$ALIAS" >> "$BASH_RC_PATH"
cd $CURRENT_PATH
