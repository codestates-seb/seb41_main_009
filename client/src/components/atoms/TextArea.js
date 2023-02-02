import styled from 'styled-components';
import { ParagraphMedium } from '../../styles/typo';

const TextArea = ({ maxLength, placeholder, onChange, height, padding }) => {
  return (
    <Container maxLength={maxLength} placeholder={placeholder} onChange={onChange} height={height} padding={padding} />
  );
};

const Container = styled.textarea`
  ${ParagraphMedium};
  padding: ${props => props.padding || '0px 0px 100px 0px'};
  height: ${props => props.height || '310px'};
  border: none;
  outline-color: white;
  overflow-x: hidden;
`;

export default TextArea;
