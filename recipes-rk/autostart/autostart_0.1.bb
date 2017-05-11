# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Set the application that will run automatically"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

USE_X11 = "${@bb.utils.contains("DISTRO_FEATURES", "x11", "yes", "no", d)}"
USE_WL = "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "yes", "no", d)}"

SRC_URI = " \
	file://S124autostart-x11.sh \
	file://S124autostart-wayland.sh \
	file://S124autostart.sh \
"
S = "${WORKDIR}"

inherit update-rc.d

do_install() {
	install -d ${D}/${bindir}/init.d

	if [ "${USE_X11}" = "yes" ]; then
		install -m 0755 ${S}/S124autostart-x11.sh ${D}/${bindir}/init.d/
	elif [ "${USE_WL}" = "yes" ]; then
		install -d ${D}/${sysconfdir}/rc5.d
		install -m 0755 ${S}/S124autostart-wayland.sh ${D}/${bindir}/init.d/
	else
		install -d ${D}/${sysconfdir}/rc5.d
		install -m 0755 ${S}/S124autostart.sh ${D}/${bindir}/init.d/
	fi
}

RDEPENDS_${PN} = "bash"

INITSCRIPT_NAME = "dvfs-rules.sh"
INITSCRIPT_PARAMS = "start 100 5 3 ."
