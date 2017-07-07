# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SRC_URI += " \
	file://autostart-player.sh \
"

do_install_append () {
	if [ "${USE_X11}" = "yes" ]; then
		bberror "Please use rk-none distro"
        exit 1
	elif [ "${USE_WL}" = "yes" ]; then
		bberror "Please use rk-none distro"
        exit 1
	else
		install -m 0755 ${S}/autostart-player.sh ${D}/${sysconfdir}/init.d/autostart.sh
	fi
}

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
FILESPATH_prepend := "${THISDIR}/${PN}:"
