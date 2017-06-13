# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

# whether you want to update loader
UPDATE_LOADER ?= "no"
# The storage you want to update
TAEGT_DEVICE ?= "/dev/mmcblk2"

SUMMARY = "Rockchip recovery service for SD-Card firmware update."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
	file://recovery-service.sh \
	file://update-gpt.img \
"
S = "${WORKDIR}"

inherit update-rc.d

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/recovery-service.sh ${D}${sysconfdir}/init.d/recovery-service.sh

	cp ${WORKDIR}/update-gpt.img ${D}/update-gpt.img

	sed -e "s,UPDATE_LOADER=.*,UPDATE_LOADER=${UPDATE_LOADER},g" \
		-i ${D}${sysconfdir}/init.d/recovery-service.sh

	sed -e "s,TAEGT_DEVICE=.*,TAEGT_DEVICE=${TAEGT_DEVICE},g" \
		-i ${D}${sysconfdir}/init.d/recovery-service.sh
}

FILES_${PN} += "/*"

RDEPENDS_${PN} = "bash"

INITSCRIPT_NAME = "recovery-service.sh"
INITSCRIPT_PARAMS = "start 24 5 3 ."
