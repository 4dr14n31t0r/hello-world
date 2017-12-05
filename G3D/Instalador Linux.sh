#Si quieres descargar una version mas actualizada
#sólo tienes que cambiar 3.2.1 por la version de GLFW
#que quieras instalar en su lugar.

set version=3.2.1 && \
wget "https://github.com/glfw/glfw/releases/download/${version}/glfw-${version}.zip" && \
unzip glfw-${version}.zip && \
cd glfw-${version}.zip && \
sudo apt-get install -y cmake xorg-dev libglu1-mesa-dev && \
sudo cmake -G "Unix Makefiles" && \
sudo make && \
sudo make install
