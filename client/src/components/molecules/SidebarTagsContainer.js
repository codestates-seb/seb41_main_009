import styled from 'styled-components';

const SidebarTagsContainer = ({ isClicked }) => {
  return <Container isClicked={isClicked}>Abc</Container>;
};

const Container = styled.div`
  display: ${props => (props.isClicked ? 'flex' : 'none')};
`;

export default SidebarTagsContainer;
