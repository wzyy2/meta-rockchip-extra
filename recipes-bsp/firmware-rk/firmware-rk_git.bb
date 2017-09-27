# Copyright (C) 2016 - 2017 Jacob Chen <jacob2.chen@rock-chips.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Rockchip firmware"
DESCRIPTION = "Rockchip firmware such as for the WIFI, BT"

LICENSE = "BINARY"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=564e729dd65db6f65f911ce0cd340cf9"
NO_GENERIC_LICENSE[BINARY] = "LICENSE.TXT"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/rockchip-linux/rkbin.git \
		file://test.mp4"
S = "${WORKDIR}/git"

inherit allarch

do_install () {
	install -d ${D}/system/etc/firmware/
	cp -rf ${S}/firmware/wifi/* ${D}/system/etc/firmware/
	install -d ${D}/mnt
	cp -rf ${WORKDIR}/test.mp4 ${D}/mnt

	install -d ${D}/etc/firmware/
	cp -rf ${S}/firmware/bluetooth/*.hcd ${D}/etc/firmware/
}

PACKAGES =+ "${PN}-wifi \
	${PN}-bt \
"

FILES_${PN}-wifi = "/system/etc/* /mnt/test.mp4"
FILES_${PN}-bt = "/etc/firmware/*"
