FROM debian:bullseye-slim

# Install necessary packages
RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-11-jre-headless wget && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Create a user and group for Jackrabbit
RUN groupadd -r jackrabbit && useradd -r -g jackrabbit -d /opt/jackrabbit -s /sbin/nologin jackrabbit

# Create Jackrabbit directory
RUN mkdir -p /opt/jackrabbit && chown -R jackrabbit:jackrabbit /opt/jackrabbit

# Set user to Jackrabbit
USER jackrabbit

# Set working directory
WORKDIR /opt/jackrabbit

# Download and install Jackrabbit
ARG JACKRABBIT_VERSION=2.22.0
RUN wget https://dlcdn.apache.org/jackrabbit/${JACKRABBIT_VERSION}/jackrabbit-standalone-${JACKRABBIT_VERSION}.jar -O /opt/jackrabbit/jackrabbit.jar

# Expose port
EXPOSE 8080

# Start Jackrabbit
CMD ["java", "-jar", "jackrabbit.jar"]
