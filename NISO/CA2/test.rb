def hello(*args)
    "Hello " + args.join(' ')
end

puts hello.__send__(hello, 'hi')