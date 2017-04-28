#! /bin/bash
### BEGIN INIT INFO
# Provides: recovery service
# Description: recovery service for SD-Card firmware update
### END INIT INFO

COLOR_ERROR='\033[01;31m'
COLOR_DEBUG='\033[01;34m'
COLOR_NOTIFY='\033[01;33m'
COLOR_RESET='\033[00;00m'

UPDATE_LOADER=no
GPT_IMAGE_LOCATION=/update-gpt.img
TAEGT_DEVICE=/dev/device_node

case $1 in
	start)
		[ -e /sys/class/leds/led0 ] && echo heartbeat >/sys/class/leds/led0/trigger

		echo -e "${COLOR_NOTIFY}----------------------------------------------------!${COLOR_RESET}"
		echo -e "${COLOR_NOTIFY}Start Burning Image!${COLOR_RESET}"
		echo -e "${COLOR_NOTIFY}----------------------------------------------------!${COLOR_RESET}"

		if [ "${UPDATE_LOADER}" = "no" ]; then
			dd if=${GPT_IMAGE_LOCATION} of=${TAEGT_DEVICE} skip=24576 seek=24576
		else
			dd if=${GPT_IMAGE_LOCATION} of=${TAEGT_DEVICE}
		fi

		if [ $? -ne 0 ]; then
			echo -e "${COLOR_ERROR}Failed to burn the Image.${COLOR_RESET}"
			exit
		fi

		echo -e "${COLOR_NOTIFY}----------------------------------------------------!${COLOR_RESET}"
		echo -e "${COLOR_NOTIFY}Wait Sync......${COLOR_RESET}"
		echo -e "${COLOR_NOTIFY}----------------------------------------------------!${COLOR_RESET}"

		sync

		echo -e "${COLOR_NOTIFY}----------------------------------------------------!${COLOR_RESET}"
		echo -e "${COLOR_NOTIFY}Update Done!!${COLOR_RESET}"
		echo -e "${COLOR_NOTIFY}----------------------------------------------------!${COLOR_RESET}"

		[ -e /sys/class/leds/led0 ] && echo none >/sys/class/leds/led0/trigger
		;;
esac

exit 0
