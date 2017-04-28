# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = " \
	This image is used for SD-Card firmware update to rockchip-gpt-image. \
	Insert SD-card and press maskrom button, the board will boot into this image. \
	If you don't have a maskrom button, then please change SD-card boot order in u-boot. \
"

IMAGE_FEATURES_remove = "x11 splash"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_EXTRA_INSTALL += " \
	recovery \
"

CORE_IMAGE_EXTRA_INSTALL_remove += " \
	connman \
"
