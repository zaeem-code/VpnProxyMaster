

package com.vpn.technology.util.io.pem;

public interface PemObjectGenerator
{
    PemObject generate()
        throws PemGenerationException;
}
