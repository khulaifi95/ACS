
FROM pklehre/niso2020-lab2-msc

ADD GP.py /bin
ADD Lab2-MSc-Time-Series.pdf /bin
ADD ParseTool.py /bin
ADD data.txt /bin
ADD dummy_data.txt /bin

RUN apt-get update
RUN apt-get -y install python-numpy

CMD ["-username", "zxx992", "-verbose", "-submission", "python /bin/GP.py", "-questions","3"]