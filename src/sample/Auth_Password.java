package sample;

public class Auth_Password 
{
	public static void main(String args[])
	{
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			jSch.addIdentity(privateKeyLocationonLocaleSystem);
			session = jSch.getSession(sftpinfo.getUsername(), sftpinfo.getHost(), sftpinfo.getPort());

			Properties config = new Properties();
			config.put(STRICT_HOST_KEY_CHECKING, STRICT_HOST_KEY_CHECK_VAL);
			session.setConfig(config);
			session.connect();
			channel = session.openChannel(SFTP);
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(sftpinfo.getFolder());
			return channelSftp;
		} catch (JSchException | SftpException e) {
			throw new SFTPException.CreateSFTPSessionException(e, SFTPErrorCode.CREATESFTPSESSIONFAILED);
		} finally {
			if (channelSftp != null) {
				channelSftp.disconnect();
				channelSftp.exit();
			}
			if (channel != null)
				channel.disconnect();

			if (session != null)
				session.disconnect();
		}

	}
}
