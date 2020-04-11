
FROM pklehre/niso2020-lab2-msc

ADD zxx992.py /bin
ADD ParseTool.py /bin
ADD data.txt /bin
ADD Question_4_5.pdf /bin

RUN apt-get update
RUN apt-get -y install python-numpy

CMD ["-username", "zxx992", "-verbose", "-submission", "python /bin/zxx992.py"]
