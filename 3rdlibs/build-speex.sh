#!/bin/sh

set -xe

VERSION="1.2rc1"
BUILDDIR=`pwd`
OGGDIR="libogg-built"
DESTDIR="libspeex-built"
LIBS="libspeex.a libspeexdsp.a"
ARCHS="i386 x86_64 armv7 armv7s arm64"

rm -rf $DESTDIR
mkdir $DESTDIR

if [ ! -e "speex-$VERSION.tar.gz" ]; then
    curl -LO http://downloads.xiph.org/releases/speex/speex-$VERSION.tar.gz
fi

tar zxf speex-$VERSION.tar.gz
cd speex-$VERSION


./configure

for ARCH in $ARCHS;
do
    mkdir -p ../$DESTDIR/$ARCH

    make distclean

    IOSMV="-miphoneos-version-min=4.3"
    case $ARCH in
    arm*)
        if [ $ARCH == "arm64" ]; then
            IOSMV="-miphoneos-version-min=7.0"
        fi
        PATH=`xcodebuild -version -sdk iphoneos PlatformPath`"/Developer/usr/bin:$PATH" \
        SDK=`xcodebuild -version -sdk iphoneos Path` \
        CC="xcrun --sdk iphoneos clang -arch $ARCH $IOSMV --sysroot=$SDK -isystem $SDK/usr/include" \
        CXX="xcrun --sdk iphoneos clang++ -arch $ARCH $IOSMV --sysroot=$SDK -isystem $SDK/usr/include" \
        LDFLAGS="-Wl,-syslibroot,$SDK" \
        ./configure \
        --host=arm-apple-darwin \
        --prefix=$BUILDDIR/$DESTDIR/$ARCH \
        --with-ogg=$BUILDDIR/$OGGDIR/$ARCH
        ;;
    *)
        PATH=`xcodebuild -version -sdk iphonesimulator PlatformPath`"/Developer/usr/bin:$PATH" \
        CC="xcrun --sdk iphonesimulator clang -arch $ARCH $IOSMV" \
        CXX="xcrun --sdk iphonesimulator clang++ -arch $ARCH $IOSMV" \
        ./configure \
        --prefix=$BUILDDIR/$DESTDIR/$ARCH \
        --with-ogg=$BUILDDIR/$OGGDIR/$ARCH
        ;;
    esac

    make
    make install
done

make distclean

cd ..
mkdir -p $DESTDIR/universal/lib

for LIB in $LIBS;
do
    INPUT=""
    for ARCH in $ARCHS;
    do
        INPUT="$INPUT $DESTDIR/$ARCH/lib/$LIB"
    done
    lipo -create $INPUT -output $DESTDIR/universal/lib/$LIB
done