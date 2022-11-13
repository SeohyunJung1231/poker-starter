# Getting Started

### 0. Development Environment Settings

* java: corretto-11.0.16.1
* mysql: docker
  ```aidl
  # install docker
  yay -S docker
  sudo usermod -aG docker $USER
  sudo usermod -aG docker ec2-user
  systemctl start docker
  
  # install docker-compose
  sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  sudo chmod +x /usr/local/bin/docker-compose
  sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
    
  # run docker
  cd src/docker
  docker-compose up -d
  ```
