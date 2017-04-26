# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A image with Rockchip's multimedia packages."

require recipes-rk/images/rk-image-base.bb

# Add gstreamer1.0-libav if you need AAC Audio decoder.

CORE_IMAGE_EXTRA_INSTALL += " \
	alsa-utils \
	libdrm-rockchip \
	packagegroup-rk-gstreamer-full \
"
