FROM node:20

ARG TZ
ENV TZ="$TZ"

# Install basic development tools and iptables/ipset
RUN apt update && apt install -y \
  less \
  git \
  procps \
  sudo \
  fzf \
  zsh \
  man-db \
  unzip \
  gnupg2 \
  gh \
  iptables \
  ipset \
  iproute2 \
  dnsutils \
  aggregate \
  curl \
  jq \
  wget \
  docker.io \
  docker-compose

# Install Java and Kotlin development tools for MCP-JCR project
RUN apt update && apt install -y \
  openjdk-17-jdk \
  && rm -rf /var/lib/apt/lists/*

# Install Gradle (specific version for consistency)
RUN wget https://services.gradle.org/distributions/gradle-8.10-bin.zip -P /tmp \
  && unzip -d /opt/gradle /tmp/gradle-8.10-bin.zip \
  && rm /tmp/gradle-8.10-bin.zip
ENV GRADLE_HOME=/opt/gradle/gradle-8.10
ENV PATH=$PATH:$GRADLE_HOME/bin

# Set proper Java home for JDK 17
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

# Ensure default node user has access to /usr/local/share
RUN mkdir -p /usr/local/share/npm-global && \
  chown -R node:node /usr/local/share

ARG USERNAME=node

# Fix Docker socket permissions by adding node user to root group
# Docker socket typically belongs to root:root with 660 permissions
RUN usermod -aG docker node

# Persist bash history.
RUN SNIPPET="export PROMPT_COMMAND='history -a' && export HISTFILE=/commandhistory/.bash_history" \
  && mkdir /commandhistory \
  && touch /commandhistory/.bash_history \
  && chown -R $USERNAME /commandhistory

# Set git user name and email and disable signing
RUN git config --global user.name "Claude" && \
git config --global user.email "claude@anthropic.com" && \
git config --global commit.gpgsign false

# Set `DEVCONTAINER` environment variable to help with orientation
ENV DEVCONTAINER=true
# Set `EDITOR` so that git etc open editord in the devcontainer window
ENV EDITOR=code

# Create workspace and config directories and set permissions
RUN mkdir -p /workspace /home/node/.claude /home/node/.gradle && \
chown -R node:node /workspace /home/node/.claude /home/node/.gradle

# Create gh config directory and set permissions
RUN mkdir -p /home/node/.config/gh \
  && chown -R node:node /home/node/.config/gh

WORKDIR /workspace

RUN ARCH=$(dpkg --print-architecture) && \
  wget "https://github.com/dandavison/delta/releases/download/0.18.2/git-delta_0.18.2_${ARCH}.deb" && \
  sudo dpkg -i "git-delta_0.18.2_${ARCH}.deb" && \
  rm "git-delta_0.18.2_${ARCH}.deb"

# Install essential npm packages for Claude Code (as root before switching user)
RUN npm install -g \
  @anthropic-ai/claude-code

# Copy and set up firewall script
COPY init-firewall.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/init-firewall.sh && \
  echo "node ALL=(root) NOPASSWD: /usr/local/bin/init-firewall.sh" > /etc/sudoers.d/node-firewall && \
  chmod 0440 /etc/sudoers.d/node-firewall

# Set up non-root user
USER node

# Set the default shell to zsh rather than sh
ENV SHELL=/bin/zsh

# Default powerline10k theme
RUN sh -c "$(wget -O- https://github.com/deluan/zsh-in-docker/releases/download/v1.2.0/zsh-in-docker.sh)" -- \
  -p git \
  -p fzf \
  -a "source /usr/share/doc/fzf/examples/key-bindings.zsh" \
  -a "source /usr/share/doc/fzf/examples/completion.zsh" \
  -a "export PROMPT_COMMAND='history -a' && export HISTFILE=/commandhistory/.bash_history" \
  -x

# Volumes
VOLUME /home/node/.config/gh
VOLUME /home/node/.claude
VOLUME /home/node/.gradle
VOLUME /commandhistory
VOLUME /workspace
