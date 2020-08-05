FluidPCA : FluidRTDataClient {

  *new {|server, numDimensions = 2|
		^super.new1(server,[\numDimensions,numDimensions]);
	}

    fit{|dataSet, action|
        this.prSendMsg(\fit,[dataSet.asSymbol], action);
    }

    transform{|sourceDataSet, destDataSet, action|
		 this.prSendMsg(\transform,[sourceDataSet.asSymbol, destDataSet.asSymbol], action, [numbers(FluidMessageResponse,_,1,_)]);
    }

    fitTransform{|sourceDataSet, destDataSet, action|
		this.prSendMsg(\fitTransform,[sourceDataSet.asSymbol, destDataSet.asSymbol], action, [numbers(FluidMessageResponse,_,1,_)]);
    }

    transformPoint{|sourceBuffer, destBuffer, action|
        sourceBuffer = this.prEncodeBuffer(sourceBuffer);
        destBuffer = this.prEncodeBuffer(destBuffer);
        this.prSendMsg(\transformPoint,[sourceBuffer, destBuffer], action, outputBuffers:[destBuffer]);
    }
}
