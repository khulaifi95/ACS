
FROM pklehre/niso2020-lab2-msc

ADD s-expression.py /bin

RUN apt-get update
RUN apt-get -y install python-numpy

CMD ["-username", "zxx992", "-verbose", "-submission", "python /bin/s-expression.py", "-questions", "1"]
