# Copyright (C) 2016 - 2017 Jacob Chen <jacob2.chen@rock-chips.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Memory accesses tool"
DESCRIPTION = "Simple app to do memory accesses via /dev/mem."

LICENSE = "CLOSED"

SRC_URI = "file://io.c"
S = "${WORKDIR}"

do_compile() {
         ${CC} io.c -o io
}

do_install() {
         install -d ${D}${bindir}
         install -m 0755 io ${D}${bindir}
}

INSANE_SKIP_${PN} = "ldflags"