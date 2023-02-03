import { INVALID_PASSWORD } from '../../../constants/Messages';
import { Box, Label, SignupInput } from '../../atoms/signup/SignupComponents';
import { isValidPassword } from '../../../functions/isValid';

const PasswordBox = ({ setPassword, passwordMessage, setPasswordMessage }) => {
  const onPasswordInput = e => {
    const passwordValue = e.target.value;

    setPassword(passwordValue);

    if (isValidPassword(passwordValue) || passwordValue.length === 0) {
      setPasswordMessage('');
    } else {
      setPasswordMessage(INVALID_PASSWORD);
    }
  };

  return (
    <Box>
      <Label>Password</Label>
      <SignupInput
        type="password"
        placeholder="Enter Your Password"
        onChange={onPasswordInput}
        message={passwordMessage}
      />
    </Box>
  );
};

export default PasswordBox;
